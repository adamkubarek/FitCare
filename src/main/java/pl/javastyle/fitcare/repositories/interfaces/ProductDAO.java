package pl.javastyle.fitcare.repositories.interfaces;

import pl.javastyle.fitcare.core.CrudBaseOperations;
import pl.javastyle.fitcare.domain.Product;

import java.util.List;

public interface ProductDAO extends CrudBaseOperations<Product> {
    List<Product> getAllProducts();
}
