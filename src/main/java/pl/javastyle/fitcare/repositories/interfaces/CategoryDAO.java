package pl.javastyle.fitcare.repositories.interfaces;

import pl.javastyle.fitcare.domain.Category;

import java.util.List;

public interface CategoryDAO {
    Category save(Category category);
    Category read(Long categoryId);
    Category findCategoryByName(String name);
    Category delete(Long categoryId);
    List<Category> getAllCategories();
}
