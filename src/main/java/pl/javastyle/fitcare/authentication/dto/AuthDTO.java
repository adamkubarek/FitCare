package pl.javastyle.fitcare.authentication.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.javastyle.fitcare.authentication.domain.Auth;
import pl.javastyle.fitcare.authentication.domain.Role;
import pl.javastyle.fitcare.core.BaseDTO;
import pl.javastyle.fitcare.user.UserDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class AuthDTO extends BaseDTO {

    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 8, max = 25)
    private String password;
    private Set<Role> roles;

    @Valid
    private UserDTO userDTO;

    public AuthDTO(Auth auth) {
        setId(auth.getId());
        this.email = auth.getEmail();
        this.password = auth.getPassword();
    }
}
