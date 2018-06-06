package pl.javastyle.FitCare.services.interfaces;

import pl.javastyle.FitCare.rest.dto.ProductDTO;
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
