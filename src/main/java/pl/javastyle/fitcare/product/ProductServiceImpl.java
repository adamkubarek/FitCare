package pl.javastyle.fitcare.product;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.javastyle.fitcare.core.Context;
import pl.javastyle.fitcare.core.Mapper;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private Mapper<Product, ProductDTO> mapper;


    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.mapper = new ProductMapper();
    }

    @Override
    public ProductDTO getProductById(Long id) {
        return mapper.domainToDto(productRepository.read(id));
    }

    @Override
    public ProductDTO addNewProduct(ProductDTO productDTO) {
        Product product = mapper.dtoToDomain(productDTO, Context.getUser());
        Product addedProduct = productRepository.save(product);

        return mapper.domainToDto(addedProduct);
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO, Long productId) {
        productDTO.setId(productId);
        Product savedProduct = productRepository.save(mapper.dtoToDomain(productDTO, null));
        return mapper.domainToDto(savedProduct);
    }

    @Override
    public ProductDTO patchProduct(ProductDTO patcher, Long productId) {
        Product product = productRepository.read(productId);

        product.fillWithPatcherProperties(patcher);

        return mapper.domainToDto(productRepository.save(product));
    }

    @Override
    public ProductDTO deleteProduct(Long id) {
        return mapper.domainToDto(productRepository.delete(id));
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
        return productRepository.getAllProducts(Context.getUser()).stream()
                .map(mapper::domainToDto)
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
