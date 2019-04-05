package pl.javastyle.fitcare.authentication.services;

import pl.javastyle.fitcare.authentication.dto.JWTResponseDTO;
import pl.javastyle.fitcare.authentication.dto.LoginDTO;

public interface LoginService {
    JWTResponseDTO authenticateUser(LoginDTO loginDTO);
}
