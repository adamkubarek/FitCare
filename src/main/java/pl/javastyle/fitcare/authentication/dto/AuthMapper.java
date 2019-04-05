package pl.javastyle.fitcare.authentication.dto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.javastyle.fitcare.authentication.domain.Auth;
import pl.javastyle.fitcare.core.Mapper;
import pl.javastyle.fitcare.user.User;
import pl.javastyle.fitcare.user.UserDTO;

public class AuthMapper implements Mapper<Auth, AuthDTO> {

    @Override
    public Auth dtoToDomain(AuthDTO authDTO) {
        UserDTO userDTO = authDTO.getUserDTO();
        Auth auth = new Auth();

        auth.setId(authDTO.getId());
        auth.setEmail(authDTO.getEmail());
        auth.setPassword(new BCryptPasswordEncoder().encode(authDTO.getPassword()));
        auth.setRoles(authDTO.getRoles());

        User user = new User();
        user.setName(userDTO.getName());
        user.setAge(userDTO.getAge());
        user.setWeight(userDTO.getWeight());
        user.setHeight(userDTO.getHeight());
        user.setActivityRate(userDTO.getActivityRate());
        user.setDietGoal(userDTO.getDietGoal());
        user.setGender(userDTO.getGender());

        auth.setUser(user);

        return auth;
    }

    @Override
    public AuthDTO domainToDto(Auth auth) {
        AuthDTO authDTO = new AuthDTO();

        authDTO.setId(auth.getId());
        authDTO.setEmail(auth.getEmail());
        authDTO.setPassword(auth.getPassword());

        return authDTO;
    }
}
