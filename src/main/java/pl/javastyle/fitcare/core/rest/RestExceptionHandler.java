package pl.javastyle.fitcare.core.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.javastyle.fitcare.core.exceptions.ApplicationError;
import pl.javastyle.fitcare.core.exceptions.ApplicationException;
import pl.javastyle.fitcare.core.exceptions.DbErrors;
import pl.javastyle.fitcare.core.exceptions.ValidationErrors;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({ApplicationException.class})
    public ResponseEntity handleApplicationException(Exception exception) {
        ApplicationError error = ((ApplicationException) exception).getError();

        HttpStatus httpStatus = determineHttpStatusResponse(error);

        return new ResponseEntity<>(error.getDescription(), new HttpHeaders(), httpStatus);
    }

    private HttpStatus determineHttpStatusResponse(ApplicationError error) {
        HttpStatus httpStatus = HttpStatus.OK;

        if (error.equals(DbErrors.ITEM_NOT_FOUND)) {
            httpStatus = HttpStatus.NOT_FOUND;
        } else if (error.equals(DbErrors.DUPLICATED_PRODUCT_NAME) || error.equals(ValidationErrors.NOT_VALID)) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }

        return httpStatus;
    }
}
