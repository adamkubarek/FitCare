package pl.javastyle.fitcare.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.javastyle.fitcare.domain.Category;
import pl.javastyle.fitcare.domain.Product;
import pl.javastyle.fitcare.exceptions.ApplicationException;
import pl.javastyle.fitcare.exceptions.DbErrors;
import pl.javastyle.fitcare.repositories.interfaces.ProductDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;
import java.util.List;

@Repository
@Transactional
public class ProductManager implements ProductDAO {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Product saveProduct(Product product) {
        try {
            setCategoryIfAlreadyExists(product);
            if (product.isPersisted()) {
                return entityManager.merge(product);
            } else {
                entityManager.persist(product);
                return product;
            }
        } catch (ConstraintViolationException e) {
            throw new ApplicationException(DbErrors.DUPLICATED_PRODUCT_NAME);
        }
    }

    private void setCategoryIfAlreadyExists(Product product) {
        Query query =  entityManager.createQuery("SELECT c FROM Category c WHERE c.name=:name", Category.class);
        query.setParameter("name", product.getCategory().getName());
        if (!query.getResultList().isEmpty()) {
            product.setCategory((Category) query.getSingleResult());
        }
    }

    @Override
    public Product findProductById(Long productId) {
        Product product = entityManager.find(Product.class, productId);
        if (product != null) {
            return product;
        }
        throw new ApplicationException(DbErrors.PRODUCT_NOT_FOUND);
    }

    @Override
    public Product deleteProduct(Long productId) {
        Product product = entityManager.find(Product.class, productId);
        if (product != null) {
            entityManager.remove(product);
            return product;
        } else {
            throw new ApplicationException(DbErrors.PRODUCT_NOT_FOUND);
        }
    }

    @Override
    public List<Product> getAllProducts() {
        TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p", Product.class);
        if (query.getResultList().isEmpty()) {
            throw new ApplicationException(DbErrors.PRODUCT_NOT_FOUND);
        } else {
            return query.getResultList();
        }
    }

    @Override
    public Product findProductByName(String name) {
        Query query =  entityManager.createQuery("SELECT p FROM Product p WHERE p.name=:name", Product.class);
        query.setParameter("name", name);
        if (query.getResultList().isEmpty()) {
            throw new ApplicationException(DbErrors.PRODUCT_NOT_FOUND);
        } else {
            return (Product) query.getResultList().get(0);
        }
    }
}
