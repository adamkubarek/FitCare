package pl.javastyle.FitCare.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.javastyle.FitCare.domain.Category;
import pl.javastyle.FitCare.repositories.interfaces.CategoryDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class CategoryManager implements CategoryDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Category saveCategory(Category category) {
        if (category.isPersisted()) {
            return entityManager.merge(category);
        } else {
            entityManager.persist(category);
            return category;
        }
    }

    @Override
    public Category findCategoryById(Long categoryId) {
        return null;
    }

    @Override
    public Category findCategoryByName(String name) {
        Query query =  entityManager.createQuery("SELECT c FROM Category c WHERE c.name=:name", Category.class);
        query.setParameter("name", name);
        if (query.getResultList().isEmpty()) {
            throw new RuntimeException("Category not found");
        } else {
            return (Category) query.getResultList().get(0);
        }
    }

    @Override
    public Category deleteCategory(Long categoryId) {
        return null;
    }

    @Override
    public List<Category> getAllCategories() {
        return null;
    }
}
