package pl.javastyle.fitcare.product;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.javastyle.fitcare.core.AbstractCrudOperations;
import pl.javastyle.fitcare.core.Context;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
class ProductRepositoryImpl extends AbstractCrudOperations<Product> implements ProductRepository {

    public ProductRepositoryImpl() {
        super(Product.class);
    }

    @Override
    public List<Product> getAllProducts() {
        TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p WHERE p.user = :user", Product.class);
        query.setParameter("user", Context.getUser());

        return query.getResultList();
    }
}
