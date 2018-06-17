package pl.javastyle.fitcare.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.javastyle.fitcare.domain.Category;
import pl.javastyle.fitcare.exceptions.ApplicationException;
import pl.javastyle.fitcare.exceptions.DbErrors;
import pl.javastyle.fitcare.repositories.interfaces.CategoryDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.List;

@Repository
@Transactional
public class CategoryManager implements CategoryDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Category saveCategory(Category category) {
        try {
            if (category.isPersisted()) {
                return entityManager.merge(category);
            } else {
                entityManager.persist(category);
                return category;
            }
        } catch (ConstraintViolationException e) {
            throw new ApplicationException(DbErrors.DUPLICATED_CATEGORY_NAME);
        }
    }

    @Override
    public Category findCategoryById(Long categoryId) {
        return entityManager.find(Category.class, categoryId);
    }

    @Override
    public Category findCategoryByName(String name) {
        Query query =  entityManager.createQuery("SELECT c FROM Category c WHERE c.name=:name", Category.class);
        query.setParameter("name", name);
        if (query.getResultList().isEmpty()) {
            throw new ApplicationException(DbErrors.CATEGORY_NOT_FOUND);
        } else {
            return (Category) query.getSingleResult();
        }
    }

    @Override
    public Category deleteCategory(Long categoryId) {
        return null;
    }

    @Override
    public List<Category> getAllCategories() {
        return Collections.emptyList();
    }
}
