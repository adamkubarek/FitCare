package pl.javastyle.fitcare.authentication.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.javastyle.fitcare.authentication.domain.Auth;
import pl.javastyle.fitcare.authentication.domain.Role;
import pl.javastyle.fitcare.authentication.domain.RoleName;
import pl.javastyle.fitcare.authentication.dto.AuthDTO;
import pl.javastyle.fitcare.authentication.dto.AuthMapper;
import pl.javastyle.fitcare.core.Mapper;
import pl.javastyle.fitcare.user.User;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService {

    private final AuthRepository authRepository;
    private final Mapper<Auth, AuthDTO> authMapper;

    public RegistrationServiceImpl(AuthRepository authRepository) {
        this.authRepository = authRepository;
        this.authMapper = new AuthMapper();
    }

    @Override
    public Boolean registerNewUser(AuthDTO registrationForm) {
        if (authRepository.existsByEmail(registrationForm.getEmail())) {
            return false;
        }

        signUserRole(registrationForm);

        Auth userToRegister = authMapper.dtoToDomain(registrationForm, new User());
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
