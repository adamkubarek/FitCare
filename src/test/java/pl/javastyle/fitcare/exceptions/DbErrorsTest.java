package pl.javastyle.fitcare.exceptions;

import org.junit.Test;

import static org.junit.Assert.*;

public class DbErrorsTest {

    @Test
    public void shouldReturnProperErrorNameWhenThrowingException() {
        try {
            throw new ApplicationException(DbErrors.CATEGORY_NOT_FOUND);
        } catch (ApplicationException exception) {
            ApplicationError result = exception.getError();

            assertEquals(DbErrors.CATEGORY_NOT_FOUND, result);
        }
    }
}