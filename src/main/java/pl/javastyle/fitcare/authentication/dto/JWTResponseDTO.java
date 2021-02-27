package pl.javastyle.fitcare.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@AllArgsConstructor
@Getter
@Setter
public class JWTResponseDTO {

    private String token;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;
}
