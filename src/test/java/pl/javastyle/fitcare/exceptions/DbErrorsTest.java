package pl.javastyle.fitcare.exceptions;

import org.junit.Test;
import pl.javastyle.fitcare.core.exceptions.ApplicationError;
import pl.javastyle.fitcare.core.exceptions.ApplicationException;
import pl.javastyle.fitcare.core.exceptions.DbErrors;

import static org.junit.Assert.assertEquals;

public class DbErrorsTest {

    @Test
    public void shouldReturnProperErrorNameWhenThrowingException() {
        try {
            throw new ApplicationException(DbErrors.ITEM_NOT_FOUND);
        } catch (ApplicationException exception) {
            ApplicationError result = exception.getError();

            assertEquals(DbErrors.ITEM_NOT_FOUND, result);
        }
    }
}