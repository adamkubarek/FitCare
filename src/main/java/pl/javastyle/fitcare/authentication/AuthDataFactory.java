package pl.javastyle.fitcare.authentication;

import org.springframework.stereotype.Service;
import pl.javastyle.fitcare.authentication.domain.Auth;
import pl.javastyle.fitcare.authentication.dto.AuthDTO;
import pl.javastyle.fitcare.core.BaseDTO;
import pl.javastyle.fitcare.core.BaseEntity;
import pl.javastyle.fitcare.core.DataFactory;

@Service
public class AuthDataFactory implements DataFactory {

    @Override
    public BaseEntity createEntity(BaseDTO baseDTO) {
        AuthDTO authDTO = (AuthDTO) baseDTO;

        return new Auth(authDTO);
    }

    @Override
    public BaseDTO createDTO(BaseEntity baseEntity) {
        return new AuthDTO((Auth) baseEntity);
    }
}
