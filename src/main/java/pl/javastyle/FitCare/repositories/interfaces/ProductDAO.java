package pl.javastyle.FitCare.repositories.interfaces;

import pl.javastyle.FitCare.domain.Product;

import java.util.List;

public interface ProductDAO {
    Product saveProduct(Product product);
    Product findProductById(Long productId);
    Product deleteProduct(Long productId);
    List<Product> getAllProducts();
    Product findProductByName(String name);
}
