package pl.javastyle.fitcare.repositories.interfaces;

import pl.javastyle.fitcare.domain.Product;

import java.util.List;

public interface ProductDAO {
    Product findProductById(Long id);
    List<Product> getAllProducts();
    Product saveProduct(Product product);
    Product deleteProduct(Long id);
}
