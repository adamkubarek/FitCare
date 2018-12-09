package pl.javastyle.fitcare.commons.repositories;

import pl.javastyle.fitcare.commons.domain.BaseEntity;

public interface CrudBaseOperations<T extends BaseEntity> {
    T save(T item);
    T read(Long itemId);
    T delete(Long itemId);
}
