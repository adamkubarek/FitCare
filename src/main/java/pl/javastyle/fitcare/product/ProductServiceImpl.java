package pl.javastyle.fitcare.product;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductDataFactory productDataFactory;

    public ProductServiceImpl(ProductRepository productRepository, ProductDataFactory productDataFactory) {
        this.productRepository = productRepository;
        this.productDataFactory = productDataFactory;
    }

    @Override
    public ProductDTO getProductById(Long id) {
        return (ProductDTO) productDataFactory.createDTO(productRepository.read(id));
    }

    @Override
    public ProductDTO addNewProduct(ProductDTO productDTO) {
        Product product = (Product) productDataFactory.createEntity(productDTO);
        Product addedProduct = productRepository.save(product);

        return (ProductDTO) productDataFactory.createDTO(addedProduct);
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO, Long productId) {
        productDTO.setId(productId);
        Product product = (Product) productDataFactory.createEntity(productDTO);
        Product savedProduct = productRepository.save(product);

        return (ProductDTO) productDataFactory.createDTO(savedProduct);
    }

    @Override
    public ProductDTO patchProduct(ProductDTO patcher, Long productId) {
        Product product = productRepository.read(productId);

        product.fillWithPatcherProperties(patcher);

        return (ProductDTO) productDataFactory.createDTO(productRepository.save(product));
    }

    @Override
    public ProductDTO deleteProduct(Long id) {
        return (ProductDTO) productDataFactory.createDTO(productRepository.delete(id));
    }

    @Override
    public List<ProductDTO> getSortedProducts(String sortedBy) {
        List<ProductDTO> allProducts = getAllProducts();
        switch (String.valueOf(sortedBy)) {
            case "name":
                return sortAllProductsByName(allProducts);
            case "calories":
                return sortAllProductsByCalories(allProducts);
            case "category":
                return sortAllProductsByCategory(allProducts);
            default:
                return allProducts;
        }
    }

    private List<ProductDTO> getAllProducts() {
        return productRepository.getAllProducts()
                .stream()
                .map(product -> (ProductDTO) productDataFactory.createDTO(product))
                .collect(Collectors.toList());
    }

    private List<ProductDTO> sortAllProductsByCategory(List<ProductDTO> products) {
        return products.stream()
                .sorted(Comparator.comparing(ProductDTO::getCategory))
                .collect(Collectors.toList());
    }

    private List<ProductDTO> sortAllProductsByName(List<ProductDTO> products) {
        return products.stream()
                .sorted(Comparator.comparing(ProductDTO::getName))
                .collect(Collectors.toList());
    }

    private List<ProductDTO> sortAllProductsByCalories(List<ProductDTO> products) {
        return products.stream()
                .sorted(Comparator.comparing(ProductDTO::getCalories, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }
}
