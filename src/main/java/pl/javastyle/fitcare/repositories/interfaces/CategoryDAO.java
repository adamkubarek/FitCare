package pl.javastyle.fitcare.repositories.interfaces;

import pl.javastyle.fitcare.core.CrudBaseOperations;
import pl.javastyle.fitcare.domain.Category;

import java.util.List;

public interface CategoryDAO extends CrudBaseOperations<Category> {
    Category findCategoryByName(String name);
    List<Category> getAllCategories();
}
