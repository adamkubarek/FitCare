package pl.javastyle.fitcare.core;

public interface CrudBaseOperations<T extends BaseEntity> {

    T save(T item);

    T read(Long itemId);

    T delete(Long itemId);
}
