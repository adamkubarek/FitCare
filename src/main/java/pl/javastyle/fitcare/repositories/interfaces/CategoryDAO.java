package pl.javastyle.fitcare.repositories.interfaces;

import pl.javastyle.fitcare.domain.Category;

import java.util.List;

public interface CategoryDAO {
    Category saveCategory(Category category);
    Category findCategoryById(Long categoryId);
    Category findCategoryByName(String name);
    Category deleteCategory(Long categoryId);
    List<Category> getAllCategories();
}
