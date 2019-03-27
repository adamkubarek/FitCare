package pl.javastyle.fitcare.core;

public interface Mapper<T, U> {
    T dtoToDomain(U dto);
    U domainToDto(T object);
}
