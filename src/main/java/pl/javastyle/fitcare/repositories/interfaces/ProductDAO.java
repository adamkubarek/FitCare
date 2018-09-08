package pl.javastyle.fitcare.repositories.interfaces;

import pl.javastyle.fitcare.domain.Product;

import java.util.List;

public interface ProductDAO {
    Product save(Product product);
    Product read(Long id);
    List<Product> getAllProducts();
    Product delete(Long id);
}
