package pl.javastyle.fitcare.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import pl.javastyle.fitcare.core.exceptions.ApplicationException;
import pl.javastyle.fitcare.core.exceptions.DbErrors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

public abstract class AbstractCrudOperations<T extends BaseEntity> {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    private final Class<T> typeParameterClass;

    @PersistenceContext
    protected EntityManager entityManager;

    public AbstractCrudOperations(Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
    }

    @Transactional
    public T save(T item) {
        if (item.isPersisted()) {
            item.setAuditMd(LocalDateTime.now());
            return entityManager.merge(item);
        } else {
            item.setAuditCd(LocalDateTime.now());
            entityManager.persist(item);
            return item;
        }
    }

    @Transactional
    public T read(Long id) {
        T item = entityManager.find(typeParameterClass, id);

        if (item == null) {
            logger.error(String.format("Item with given id not found %d", id));
            throw new ApplicationException(DbErrors.ITEM_NOT_FOUND);
        }

        return item;
    }

    @Transactional
    public T delete(Long id) {
        T item = read(id);
        entityManager.remove(item);

        return item;
    }
}
