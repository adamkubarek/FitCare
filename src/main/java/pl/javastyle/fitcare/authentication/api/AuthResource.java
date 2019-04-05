package pl.javastyle.fitcare.authentication.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.javastyle.fitcare.authentication.dto.AuthDTO;
import pl.javastyle.fitcare.authentication.dto.LoginDTO;
import pl.javastyle.fitcare.authentication.services.LoginService;
import pl.javastyle.fitcare.authentication.services.RegistrationService;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api-v1/auth")
public class AuthResource {

    private final RegistrationService registrationService;
    private final LoginService loginService;

    public AuthResource(RegistrationService registrationService, LoginService loginService) {
        this.registrationService = registrationService;
        this.loginService = loginService;
    }

    @PostMapping("/signup")
    public ResponseEntity registerNewUser(@Valid @RequestBody AuthDTO registrationForm) {
        if (registrationService.registerNewUser(registrationForm)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(registrationService.buildErrorResponse(registrationForm), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity loginUser(@Valid @RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(loginService.authenticateUser(loginDTO));
    }
}
