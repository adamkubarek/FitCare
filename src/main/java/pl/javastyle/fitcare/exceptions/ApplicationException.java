package pl.javastyle.fitcare.exceptions;

import lombok.Getter;

public class ApplicationException extends RuntimeException {

    @Getter
    private final transient ApplicationError error;

    public ApplicationException(ApplicationError error) {
        super(error.getCode());
        this.error = error;
    }
}
