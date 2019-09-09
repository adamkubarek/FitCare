package pl.javastyle.fitcare.product;

import pl.javastyle.fitcare.core.CrudBaseOperations;

import java.util.List;

public interface ProductRepository extends CrudBaseOperations<Product> {
    List<Product> getAllProducts();
}
