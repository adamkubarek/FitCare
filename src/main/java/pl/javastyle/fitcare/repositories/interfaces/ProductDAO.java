package pl.javastyle.fitcare.repositories.interfaces;

import pl.javastyle.fitcare.domain.Product;

import java.util.List;

public interface ProductDAO {
    Product saveProduct(Product product);
    Product findProductById(Long productId);
    Product deleteProduct(Long productId);
    List<Product> getAllProducts();
    Product findProductByName(String name);
}
