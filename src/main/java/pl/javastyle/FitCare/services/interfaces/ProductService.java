package pl.javastyle.FitCare.services.interfaces;

import pl.javastyle.FitCare.controllers.DTO.ProductDTO;
import pl.javastyle.FitCare.domain.Product;

import java.util.List;

public interface ProductService {
    ProductDTO addNewProduct(ProductDTO product);
    ProductDTO deleteProduct(Product product);
    List<ProductDTO> sortAllProductsByCategory();
    List<ProductDTO> sortAllProductsByName();
    List<ProductDTO> sortAllProductsByCalories();
    ProductDTO updateProduct(ProductDTO product, Long productId);
}
