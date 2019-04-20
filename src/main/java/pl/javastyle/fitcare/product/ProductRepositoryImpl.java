package pl.javastyle.fitcare.product;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.javastyle.fitcare.category.Category;
import pl.javastyle.fitcare.core.AbstractCrudOperations;
import pl.javastyle.fitcare.user.User;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
class ProductRepositoryImpl extends AbstractCrudOperations<Product> implements ProductRepository {

    public ProductRepositoryImpl() {
        super(Product.class);
    }

    @Override
    public List<Product> getAllProducts(User user) {
        TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p WHERE p.user = :user", Product.class);
        query.setParameter("user", user);

        return query.getResultList();
    }

    @Override
    public Product save(Product product) {
        setCategoryIfExists(product);
        return super.save(product);
    }

    private void setCategoryIfExists(Product product) {
        TypedQuery<Category> query = entityManager.createQuery("SELECT c FROM Category c WHERE c.name=:name AND c.user = :user", Category.class);
        query.setParameter("name", product.getCategory().getName());
        query.setParameter("user", product.getUser());

        if (CollectionUtils.isNotEmpty(query.getResultList())) {
            product.setCategory(query.getSingleResult());
        }
    }
}
