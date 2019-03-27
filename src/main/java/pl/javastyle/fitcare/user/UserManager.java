package pl.javastyle.fitcare.user;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.javastyle.fitcare.core.AbstractCrudOperations;

@Repository
@Transactional
public class UserManager extends AbstractCrudOperations<User> implements UserDAO {

    UserManager() {
        super(User.class);
    }
}
