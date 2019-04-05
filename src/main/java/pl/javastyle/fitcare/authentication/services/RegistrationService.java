package pl.javastyle.fitcare.authentication.services;

import pl.javastyle.fitcare.authentication.dto.AuthDTO;

public interface RegistrationService {
    Boolean registerNewUser(AuthDTO authDTO);
}
