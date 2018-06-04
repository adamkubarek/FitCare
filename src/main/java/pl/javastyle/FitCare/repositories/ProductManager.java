package pl.javastyle.FitCare.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.javastyle.FitCare.domain.Product;
import pl.javastyle.FitCare.repositories.interfaces.ProductDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintViolationException;
import java.util.List;

@Repository
@Transactional
public class ProductManager implements ProductDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Product saveProduct(Product product) throws ConstraintViolationException {
        if (product.isPersisted()) {
            return entityManager.merge(product);
        } else {
            entityManager.persist(product);
            return product;
        }
    }

    @Override
    public Product findProductById(Long productId) {
        return entityManager.find(Product.class, productId);
    }

    @Override
    public Product deleteProduct(Long productId) {
        Product product = entityManager.find(Product.class, productId);
        entityManager.remove(product);
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        return entityManager.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }

    @Override
    public Product findProductByName(String name) {
        Query query =  entityManager.createQuery("SELECT p FROM Product p WHERE p.name=:name", Product.class);
        query.setParameter("name", name);
        if (query.getResultList().isEmpty()) {
            throw new RuntimeException("Product not found");
        } else {
            return (Product) query.getResultList().get(0);
        }
    }
}
