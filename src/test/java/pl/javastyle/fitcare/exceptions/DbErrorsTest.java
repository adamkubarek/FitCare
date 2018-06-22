package pl.javastyle.fitcare.exceptions;

import org.junit.Test;

import static org.junit.Assert.*;

public class DbErrorsTest {

    @Test
    public void shouldReturnProperNameWhenThrowingException() {
        try {
            throw new ApplicationException(DbErrors.CATEGORY_NOT_FOUND);
        } catch (ApplicationException e) {
            assertEquals(DbErrors.CATEGORY_NOT_FOUND, e.getError());
        }
    }
}