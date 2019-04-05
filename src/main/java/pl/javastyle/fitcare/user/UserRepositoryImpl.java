package pl.javastyle.fitcare.user;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.javastyle.fitcare.core.AbstractCrudOperations;

@Repository
@Transactional
public class UserRepositoryImpl extends AbstractCrudOperations<User> implements UserRepository {
    public UserRepositoryImpl() {
        super(User.class);
    }
}
