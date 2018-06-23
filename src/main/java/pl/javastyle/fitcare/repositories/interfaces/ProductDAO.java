package pl.javastyle.fitcare.repositories.interfaces;

import pl.javastyle.fitcare.domain.Product;

import java.util.List;

public interface ProductDAO {
    Product findProductByName(String name);
    List<Product> getAllProducts();
    Product saveProduct(Product product);
    Product deleteProduct(String productName);
}
