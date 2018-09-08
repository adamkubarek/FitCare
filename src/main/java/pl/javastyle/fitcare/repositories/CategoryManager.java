package pl.javastyle.fitcare.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.javastyle.fitcare.domain.Category;
import pl.javastyle.fitcare.exceptions.ApplicationException;
import pl.javastyle.fitcare.exceptions.DbErrors;
import pl.javastyle.fitcare.repositories.interfaces.CategoryDAO;

import javax.persistence.*;
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
        } catch (PersistenceException e) {
            throw new ApplicationException(DbErrors.DUPLICATED_CATEGORY_NAME);
        }
    }

    @Override
    public Category findCategoryById(Long categoryId) {
        return entityManager.find(Category.class, categoryId);
    }

    @Override
    public Category findCategoryByName(String name) {
        TypedQuery<Category> query =  entityManager.createQuery("SELECT c FROM Category c WHERE c.name=:name", Category.class);
        query.setParameter("name", name);

        if (query.getResultList().isEmpty()) {
            throw new ApplicationException(DbErrors.CATEGORY_NOT_FOUND);
        }

        return query.getSingleResult();
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
