package pl.javastyle.fitcare.authentication.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.javastyle.fitcare.authentication.domain.Role;
import pl.javastyle.fitcare.user.UserDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@ToString
@Getter
@Setter
public class AuthDTO {
    @JsonIgnore
    private Long id;
    @NotBlank
    private String email;
    @NotBlank
    @Size(min = 8, max = 25)
    private String password;
    private Set<Role> roles;
    @Valid
    private UserDTO userDTO;
}
