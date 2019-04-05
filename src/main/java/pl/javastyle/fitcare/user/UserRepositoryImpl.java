package pl.javastyle.fitcare.user;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.javastyle.fitcare.authentication.domain.Auth;
import pl.javastyle.fitcare.core.AbstractCrudOperations;
import pl.javastyle.fitcare.core.exceptions.ApplicationException;
import pl.javastyle.fitcare.core.exceptions.DbErrors;

import javax.persistence.TypedQuery;

@Repository
@Transactional
public class UserRepositoryImpl extends AbstractCrudOperations<User> implements UserRepository {
    public UserRepositoryImpl() {
        super(User.class);
    }

    @Override
    public User getUserFromAuth(Auth auth) {
        String sql = "SELECT e FROM User e WHERE e.auth = :auth";

        TypedQuery<User> query = entityManager.createQuery(sql, User.class);

        query.setParameter("auth", auth);

        if (CollectionUtils.isEmpty(query.getResultList())) {
            throw new ApplicationException(DbErrors.USER_NOT_FOUND);
        } else {
            return query.getSingleResult();
        }
    }
}
