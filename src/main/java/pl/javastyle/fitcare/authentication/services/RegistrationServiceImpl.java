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
import pl.javastyle.fitcare.user.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService {

    private final AuthRepository authRepository;
    private final UserRepository userRepository;
    private final Mapper<Auth, AuthDTO> authMapper;

    public RegistrationServiceImpl(AuthRepository authRepository, UserRepository userRepository) {
        this.authRepository = authRepository;
        this.userRepository = userRepository;
        this.authMapper = new AuthMapper();
    }

    @Override
    public Boolean registerNewUser(AuthDTO registrationForm) {
        if (authRepository.existsByEmail(registrationForm.getEmail())) {
            return false;
        }

        signUserRole(registrationForm);

        Auth userToRegister = authMapper.dtoToDomain(registrationForm);
        User savedUser = userRepository.save(userToRegister.getUser());
        userToRegister.setUserId(savedUser.getId());
        authRepository.save(userToRegister);

        return true;
    }

    private void signUserRole(AuthDTO registrationForm) {
        Role userRole = authRepository.findRoleByName(RoleName.ROLE_USER);
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        registrationForm.setRoles(roles);
    }
}
