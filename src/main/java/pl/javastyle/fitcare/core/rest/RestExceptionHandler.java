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
        HttpStatus httpStatus = determineHttpStatusResponse(((ApplicationException) exception).getError());

        return new ResponseEntity<>(((ApplicationException) exception).getFullErrorDescription(), new HttpHeaders(), httpStatus);
    }

    private HttpStatus determineHttpStatusResponse(ApplicationError error) {
        HttpStatus httpStatus = HttpStatus.OK;

        if (error.equals(DbErrors.ITEM_NOT_FOUND) || error.equals(DbErrors.CATEGORY_NOT_FOUND)) {
            httpStatus = HttpStatus.NOT_FOUND;
        } else if (error.equals(ValidationErrors.NOT_VALID)) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }

        return httpStatus;
    }
}
