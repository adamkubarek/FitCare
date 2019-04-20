package pl.javastyle.fitcare.category;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.javastyle.fitcare.core.AbstractCrudOperations;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
class CategoryDAOImpl extends AbstractCrudOperations<Category> implements CategoryDAO {

    public CategoryDAOImpl() {
        super(Category.class);
    }

    @Override
    public Category findCategoryByName(String name) {
        TypedQuery<Category> query = entityManager.createQuery("SELECT c FROM Category c WHERE c.name=:name", Category.class);
        query.setParameter("name", name);

        return query.getSingleResult();
    }

    @Override
    public List<Category> getAllCategories() {
        TypedQuery<Category> query = entityManager.createQuery("select c from Category c", Category.class);

        return query.getResultList();
    }
}
