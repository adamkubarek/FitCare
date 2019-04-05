package pl.javastyle.fitcare.product;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.javastyle.fitcare.authentication.domain.Auth;
import pl.javastyle.fitcare.core.Mapper;
import pl.javastyle.fitcare.user.User;
import pl.javastyle.fitcare.user.UserRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private UserRepository userRepository;
    private Mapper<Product, ProductDTO> mapper;


    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.mapper = new ProductMapper();
    }

    @Override
    public ProductDTO getProductById(Long id) {
        return mapper.domainToDto(productRepository.read(id));
    }

    @Override
    public ProductDTO addNewProduct(ProductDTO productDTO) {
        Product product = mapper.dtoToDomain(productDTO, getUserFromAuth());
        attachUserOwner(product);

        Product addedProduct = productRepository.save(product);
        return mapper.domainToDto(addedProduct);
    }

    private void attachUserOwner(Product product) {
        product.setUser(getUserFromAuth());
    }

    private User getUserFromAuth() {
        Auth auth = (Auth) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.getUserFromAuth(auth);
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
        return productRepository.getAllProducts(getUserFromAuth()).stream()
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
