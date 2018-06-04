package pl.javastyle.FitCare.services.interfaces;

import pl.javastyle.FitCare.controllers.DTO.ProductDTO;
import pl.javastyle.FitCare.domain.Product;

import java.util.List;

public interface ProductService {
    ProductDTO addNewProduct(ProductDTO product);
    Product deleteProduct(Product product);
    List<Product> sortAllProductsByCategory();
    List<Product> sortAllProductsByName();
    List<Product> sortAllProductsByCalories();
    ProductDTO updateProduct(ProductDTO product, Long productId);
}
