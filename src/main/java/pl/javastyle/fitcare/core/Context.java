package pl.javastyle.fitcare.core;

import org.springframework.security.core.context.SecurityContextHolder;
import pl.javastyle.fitcare.authentication.domain.Auth;
import pl.javastyle.fitcare.user.User;

public class Context {

    private Context() {
    }

    public static User getUser() {
        return ((Auth) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal())
                .getUser();
    }
}
