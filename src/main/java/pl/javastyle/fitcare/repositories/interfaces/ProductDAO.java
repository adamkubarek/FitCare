package pl.javastyle.fitcare.repositories.interfaces;

import pl.javastyle.fitcare.commons.repositories.CrudBaseOperations;
import pl.javastyle.fitcare.domain.Product;

import java.util.List;

public interface ProductDAO extends CrudBaseOperations<Product> {
    List<Product> getAllProducts();
}
