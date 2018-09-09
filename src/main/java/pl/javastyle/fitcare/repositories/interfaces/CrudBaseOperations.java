package pl.javastyle.fitcare.repositories.interfaces;

import pl.javastyle.fitcare.domain.BaseEntity;

public interface CrudBaseOperations<T extends BaseEntity> {
    T save(T item);
    T read(Long itemId);
    T delete(Long itemId);
}
