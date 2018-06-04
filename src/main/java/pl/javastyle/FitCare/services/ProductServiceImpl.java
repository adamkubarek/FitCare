package pl.javastyle.FitCare.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.javastyle.FitCare.controllers.DTO.ProductDTO;
import pl.javastyle.FitCare.domain.Category;
import pl.javastyle.FitCare.domain.Product;
import pl.javastyle.FitCare.repositories.interfaces.CategoryDAO;
import pl.javastyle.FitCare.repositories.interfaces.ProductDAO;
import pl.javastyle.FitCare.services.interfaces.ProductService;

import javax.validation.ConstraintViolationException;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

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
        try {
            Product addedProduct = productDAO.saveProduct(mapToProduct(productDTO));
            return mapToProductDTO(addedProduct);
        } catch (ConstraintViolationException e) {
            throw new RuntimeException("Duplicated product name");
        }
    }

    private Category checkIfCategoryExist(String categoryName) {
        return categoryDAO.findCategoryByName(categoryName);
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

    @Override
    public Product deleteProduct(Product product) {
        if (product.isPersisted()) {
            return productDAO.deleteProduct(product.getId());
        } else {
            throw new RuntimeException("Product doesn't exist");
        }
    }

    @Override
    public List<Product> sortAllProductsByCategory() {
        List <Product> products = productDAO.getAllProducts();
        products.sort(Comparator.comparing(((Function<Product, Category>)Product::getCategory).andThen(Category::getName)));
        return products;
    }

    @Override
    public List<Product> sortAllProductsByName() {
        List <Product> products = productDAO.getAllProducts();
        products.sort(Comparator.comparing(Product::getName));
        return products;
    }

    @Override
    public List<Product> sortAllProductsByCalories() {
        List <Product> products = productDAO.getAllProducts();
        products.sort(Comparator.comparing(Product::getCalories, Comparator.reverseOrder()));
        return products;
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO, Long productId) {
        Product product = mapToProduct(productDTO);
        product.setId(productId);
        Product savedProduct = productDAO.saveProduct(product);
        return mapToProductDTO(savedProduct);
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
