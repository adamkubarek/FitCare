package pl.javastyle.fitcare.authentication.services;

import pl.javastyle.fitcare.authentication.domain.Auth;
import pl.javastyle.fitcare.core.CrudBaseOperations;

public interface AuthRepository extends CrudBaseOperations<Auth> {
    Auth findByEmail(String email);
    Boolean existsByEmail(String email);
}
