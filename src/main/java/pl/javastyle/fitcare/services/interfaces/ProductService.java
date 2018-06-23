package pl.javastyle.fitcare.services.interfaces;

import pl.javastyle.fitcare.rest.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO getProductById(Long id);
    List<ProductDTO> getAllProducts();
    List<ProductDTO> sortAllProductsByCategory(List<ProductDTO> products);
    List<ProductDTO> sortAllProductsByName(List<ProductDTO> products);
    List<ProductDTO> sortAllProductsByCalories(List<ProductDTO> products);
    ProductDTO addNewProduct(ProductDTO product);
    ProductDTO updateProduct(ProductDTO product, Long productId);
    ProductDTO deleteProduct(Long id);
}
