package pl.javastyle.fitcare.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.javastyle.fitcare.domain.Category;
import pl.javastyle.fitcare.domain.Product;
import pl.javastyle.fitcare.repositories.interfaces.ProductDAO;
import pl.javastyle.fitcare.rest.dto.ProductDTO;
import pl.javastyle.fitcare.services.interfaces.ProductService;
import pl.javastyle.fitcare.services.mappers.Mapper;
import pl.javastyle.fitcare.services.mappers.ProductMapper;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private ProductDAO productDAO;
    private Mapper<Product, ProductDTO> mapper;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
        this.mapper = new ProductMapper();
    }

    @Override
    public ProductDTO getProductById(Long id) {
        return mapper.domainToDto(productDAO.findProductById(id));
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productDAO.getAllProducts().stream()
                .map(mapper::domainToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> sortAllProductsByCategory(List<ProductDTO> products) {
        return products.stream()
                .sorted(Comparator.comparing(ProductDTO::getCategory))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> sortAllProductsByName(List<ProductDTO> products) {
        return products.stream()
                .sorted(Comparator.comparing(ProductDTO::getName))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> sortAllProductsByCalories(List<ProductDTO> products) {
        return products.stream()
                .sorted(Comparator.comparing(ProductDTO::getCalories, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO addNewProduct(ProductDTO productDTO) {
        Product addedProduct = productDAO.saveProduct(mapper.dtoToDomain(productDTO));
        return mapper.domainToDto(addedProduct);
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO, Long productId) {
        productDTO.setId(productId);
        Product savedProduct = productDAO.saveProduct(mapper.dtoToDomain(productDTO));
        return mapper.domainToDto(savedProduct);
    }

    @Override
    public ProductDTO patchProduct(ProductDTO patcher, Long productId) {
        Product product = productDAO.findProductById(productId);

        updateOptionalProperties(patcher, product);

        return mapper.domainToDto(productDAO.saveProduct(product));
    }

    private void updateOptionalProperties(ProductDTO patcher, Product product) {
        if (patcher.getName() != null) {
            product.setName(patcher.getName());
        }

        if (patcher.getCategory() != null && !patcher.getCategory().isEmpty()) {
            Category category = new Category();
            category.setName(patcher.getCategory());
            product.setCategory(category);
        }

        if (patcher.getCarbs() != null) {
            product.setCarbs(patcher.getCarbs());
        }

        if (patcher.getProtein() != null) {
            product.setProtein(patcher.getProtein());
        }

        if (patcher.getFat() != null) {
            product.setFat(patcher.getFat());
        }

        if (patcher.getCalories() != null) {
            product.setCalories(patcher.getCalories());
        }
    }

    @Override
    public ProductDTO deleteProduct(Long id) {
        return mapper.domainToDto(productDAO.deleteProduct(id));
    }
}
