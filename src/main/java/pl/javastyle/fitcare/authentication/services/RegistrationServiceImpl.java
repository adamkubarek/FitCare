package pl.javastyle.fitcare.authentication.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.javastyle.fitcare.authentication.AuthDataFactory;
import pl.javastyle.fitcare.authentication.domain.Auth;
import pl.javastyle.fitcare.authentication.domain.Role;
import pl.javastyle.fitcare.authentication.domain.RoleName;
import pl.javastyle.fitcare.authentication.dto.AuthDTO;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService {

    private final AuthRepository authRepository;
    private final AuthDataFactory authDataFactory;

    public RegistrationServiceImpl(AuthRepository authRepository, AuthDataFactory authDataFactory) {
        this.authRepository = authRepository;
        this.authDataFactory = authDataFactory;
    }

    @Override
    public Boolean registerNewUser(AuthDTO registrationForm) {
        if (Boolean.TRUE.equals(authRepository.existsByEmail(registrationForm.getEmail()))) {
            return false;
        }

        signUserRole(registrationForm);
        Auth userToRegister = (Auth) authDataFactory.createEntity(registrationForm);
        authRepository.save(userToRegister);

        return true;
    }

    private void signUserRole(AuthDTO registrationForm) {
        Role userRole = authRepository.findRoleByName(RoleName.ROLE_USER);
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        registrationForm.setRoles(roles);
    }

    @Override
    public String buildErrorResponse(AuthDTO authDTO) {
        return "User registered with email " +
                authDTO.getEmail() +
                " already exist. " +
                "Please use different email to register your account.";
    }
}
