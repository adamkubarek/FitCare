package pl.javastyle.fitcare.product;

import pl.javastyle.fitcare.core.CrudBaseOperations;
import pl.javastyle.fitcare.product.Product;
import pl.javastyle.fitcare.user.User;

import java.util.List;

public interface ProductRepository extends CrudBaseOperations<Product> {
    List<Product> getAllProducts(User user);
}
