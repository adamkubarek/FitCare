package pl.javastyle.fitcare.user;

import pl.javastyle.fitcare.authentication.domain.Auth;
import pl.javastyle.fitcare.core.CrudBaseOperations;

public interface UserRepository extends CrudBaseOperations<User> {
    User getUserFromAuth(Auth auth);
}
