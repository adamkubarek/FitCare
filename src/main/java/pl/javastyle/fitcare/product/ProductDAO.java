package pl.javastyle.fitcare.product;

import pl.javastyle.fitcare.core.CrudBaseOperations;
import pl.javastyle.fitcare.product.Product;

import java.util.List;

public interface ProductDAO extends CrudBaseOperations<Product> {
    List<Product> getAllProducts();
}
