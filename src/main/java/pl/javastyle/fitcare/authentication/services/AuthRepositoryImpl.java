package pl.javastyle.fitcare.authentication.services;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.javastyle.fitcare.authentication.domain.Auth;
import pl.javastyle.fitcare.authentication.domain.Role;
import pl.javastyle.fitcare.authentication.domain.RoleName;
import pl.javastyle.fitcare.core.AbstractCrudOperations;
import pl.javastyle.fitcare.core.exceptions.ApplicationException;
import pl.javastyle.fitcare.core.exceptions.DbErrors;

import javax.persistence.TypedQuery;

@Repository
@Transactional
public class AuthRepositoryImpl extends AbstractCrudOperations<Auth> implements AuthRepository {
    public AuthRepositoryImpl() {
        super(Auth.class);
    }

    @Override
    public Auth findByEmail(String email) {
        String sql = "SELECT e FROM Auth e WHERE e.email = :email";

        TypedQuery<Auth> query = entityManager.createQuery(sql, Auth.class);

        query.setParameter("email", email);

        if (CollectionUtils.isEmpty(query.getResultList())) {
            throw new UsernameNotFoundException("User not found with given email: " + email);
        } else {
            return query.getSingleResult();
        }
    }

    @Override
    public Boolean existsByEmail(String email) {
        try {
            findByEmail(email);
            return true;
        } catch (UsernameNotFoundException e) {
            return false;
        }
    }

    @Override
    public Role findRoleByName(RoleName name) {
        String sql = "SELECT e FROM Role e where e.name = :name";

        TypedQuery<Role> query = entityManager.createQuery(sql, Role.class);

        query.setParameter("name", name);

        if (CollectionUtils.isEmpty(query.getResultList())) {
            throw new ApplicationException(DbErrors.ITEM_NOT_FOUND);
        } else {
            return query.getSingleResult();
        }
    }
}
