package pl.javastyle.fitcare.services.mappers;

public interface Mapper<T, U> {
    T dtoToDomain (U dto);
    U domainToDto (T object);
}
