package pl.javastyle.fitcare.commons.services;

public interface Mapper<T, U> {
    T dtoToDomain (U dto);
    U domainToDto (T object);
}
