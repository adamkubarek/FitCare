package pl.javastyle.fitcare.authentication.services;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AuthRepositoryImpl authRepository;

    public UserDetailsServiceImpl(AuthRepositoryImpl authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        return authRepository.findByEmail(email);
    }
}
