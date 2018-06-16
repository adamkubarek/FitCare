package pl.javastyle.fitcare.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.javastyle.fitcare.rest.dto.ProductDTO;
import pl.javastyle.fitcare.domain.Category;
import pl.javastyle.fitcare.domain.Product;
import pl.javastyle.fitcare.repositories.interfaces.CategoryDAO;
import pl.javastyle.fitcare.repositories.interfaces.ProductDAO;
import pl.javastyle.fitcare.services.interfaces.ProductService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private ProductDAO productDAO;
    private CategoryDAO categoryDAO;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO, CategoryDAO categoryDAO) {
        this.productDAO = productDAO;
        this.categoryDAO = categoryDAO;
    }

    @Override
    public ProductDTO addNewProduct(ProductDTO productDTO) {
        Product addedProduct = productDAO.saveProduct(mapToProduct(productDTO));
        return mapToProductDTO(addedProduct);
    }

    private Product mapToProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setCalories(productDTO.getCalories());
        product.setCarbs(productDTO.getCarbs());
        product.setProtein(productDTO.getProtein());
        product.setFat(productDTO.getFat());
        Category category = checkIfCategoryExist(productDTO.getCategory());
        product.setCategory(category);
        return product;
    }

    private Category checkIfCategoryExist(String categoryName) {
        return categoryDAO.findCategoryByName(categoryName);
    }

    @Override
    public ProductDTO deleteProduct(Product product) {
        return mapToProductDTO(productDAO.deleteProduct(product.getId()));
    }

    @Override
    public List<ProductDTO> sortAllProductsByCategory() {
        return productDAO.getAllProducts().stream()
                .map(this::mapToProductDTO)
                .sorted(Comparator.comparing(ProductDTO::getCategory))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> sortAllProductsByName() {
        return productDAO.getAllProducts().stream()
                .map(this::mapToProductDTO)
                .sorted(Comparator.comparing(ProductDTO::getName))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> sortAllProductsByCalories() {
        return productDAO.getAllProducts().stream()
                .map(this::mapToProductDTO)
                .sorted(Comparator.comparing(ProductDTO::getCalories, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO, Long productId) {
        Product savedProduct = productDAO.saveProduct(mapToProduct(productDTO, productId));
        return mapToProductDTO(savedProduct);
    }

    private Product mapToProduct(ProductDTO productDTO, Long productId) {
        Product product = mapToProduct(productDTO);
        product.setId(productId);
        return product;
    }

    private ProductDTO mapToProductDTO(Product savedProduct) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(savedProduct.getId());
        productDTO.setCalories(savedProduct.getCalories());
        productDTO.setCarbs(savedProduct.getCarbs());
        productDTO.setCategory(savedProduct.getCategory().getName());
        productDTO.setFat(savedProduct.getFat());
        productDTO.setName(savedProduct.getName());
        productDTO.setProtein(savedProduct.getProtein());
        return productDTO;
    }
}
