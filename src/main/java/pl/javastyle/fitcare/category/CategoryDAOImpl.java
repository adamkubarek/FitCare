package pl.javastyle.fitcare.category;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.javastyle.fitcare.core.AbstractCrudOperations;
import pl.javastyle.fitcare.core.exceptions.ApplicationException;
import pl.javastyle.fitcare.core.exceptions.DbErrors;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
class CategoryDAOImpl extends AbstractCrudOperations<Category> implements CategoryDAO {

    public CategoryDAOImpl() {
        super(Category.class);
    }

    @Override
    public Category save(Category item) {
        try {
            return super.save(item);
        } catch (PersistenceException e) {
            throw new ApplicationException(DbErrors.DUPLICATED_CATEGORY_NAME);
        }
    }

    @Override
    public Category findCategoryByName(String name) {
        TypedQuery<Category> query = entityManager.createQuery("SELECT c FROM Category c WHERE c.name=:name", Category.class);
        query.setParameter("name", name);

        if (query.getResultList().isEmpty()) {
            throw new ApplicationException(DbErrors.ITEM_NOT_FOUND);
        }

        return query.getSingleResult();
    }

    @Override
    public List<Category> getAllCategories() {
        TypedQuery<Category> query = entityManager.createQuery("select c from Category c", Category.class);
        if (query.getResultList().isEmpty()) {
            throw new ApplicationException(DbErrors.ITEMS_NOT_FOUND);
        }

        return query.getResultList();
    }
}
