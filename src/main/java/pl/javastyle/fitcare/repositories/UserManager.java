package pl.javastyle.fitcare.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.javastyle.fitcare.core.AbstractCrudOperations;
import pl.javastyle.fitcare.domain.User;
import pl.javastyle.fitcare.repositories.interfaces.UserDAO;

@Repository
@Transactional
public class UserManager extends AbstractCrudOperations<User> implements UserDAO {

    UserManager() {
        super(User.class);
    }
}
