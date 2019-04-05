package pl.javastyle.fitcare.core;

import pl.javastyle.fitcare.user.User;

public interface Mapper<T, U> {
    T dtoToDomain(U dto, User user);
    U domainToDto(T object);
}
