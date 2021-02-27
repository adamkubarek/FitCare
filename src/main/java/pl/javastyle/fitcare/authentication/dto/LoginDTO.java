package pl.javastyle.fitcare.authentication.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class LoginDTO {

    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 8, max = 25)
    private String password;
}
