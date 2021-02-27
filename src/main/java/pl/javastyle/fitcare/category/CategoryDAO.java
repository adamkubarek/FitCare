package pl.javastyle.fitcare.category;

import pl.javastyle.fitcare.core.CrudBaseOperations;

import java.util.List;

public interface CategoryDAO extends CrudBaseOperations<Category> {
    Category findCategoryByName(String name);

    List<Category> getAllCategories();
}
