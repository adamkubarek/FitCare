package pl.javastyle.fitcare.rest.exceptionhandling;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.javastyle.fitcare.core.exceptions.ApplicationException;
import pl.javastyle.fitcare.core.exceptions.DbErrors;
import pl.javastyle.fitcare.core.exceptions.ValidationErrors;
import pl.javastyle.fitcare.core.rest.RestExceptionHandler;

import static org.junit.Assert.assertEquals;

public class RestExceptionHandlerTest {

    private RestExceptionHandler handler;

    @Before
    public void setup() {
        handler = new RestExceptionHandler();
    }

    @Test
    public void shouldReturnBadRequestStatusWhenThrowingValidationError() {
        try {
            throw new ApplicationException(ValidationErrors.NOT_VALID);
        } catch (ApplicationException exception) {
            ResponseEntity entity = handler.handleApplicationException(exception);
            HttpStatus result = entity.getStatusCode();

            assertEquals(HttpStatus.BAD_REQUEST, result);
        }
    }

    @Test
    public void shouldReturnNotFoundStatusWhenProductNotFound() {
        try {
            throw new ApplicationException(DbErrors.ITEM_NOT_FOUND);
        } catch (ApplicationException exception) {
            ResponseEntity entity = handler.handleApplicationException(exception);
            HttpStatus result = entity.getStatusCode();

            assertEquals(HttpStatus.NOT_FOUND, result);
        }
    }
}