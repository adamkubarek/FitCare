package pl.javastyle.fitcare.authentication.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.javastyle.fitcare.authentication.dto.AuthDTO;
import pl.javastyle.fitcare.core.BaseEntity;
import pl.javastyle.fitcare.user.User;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Table(name = "auths", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "email"
        })
})
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Auth extends BaseEntity implements UserDetails {

    private String email;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "authentication_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User user;

    public Auth(AuthDTO authDTO) {
        setId(authDTO.getId());
        this.email = authDTO.getEmail();
        this.password = new BCryptPasswordEncoder().encode(authDTO.getPassword());
        this.roles = authDTO.getRoles();
        this.user = new User(authDTO.getUserDTO());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
