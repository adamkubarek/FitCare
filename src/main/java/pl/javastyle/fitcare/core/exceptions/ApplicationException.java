package pl.javastyle.fitcare.core.exceptions;

import lombok.Getter;

public class ApplicationException extends RuntimeException {

    @Getter
    private final transient String fullErrorDescription;

    @Getter
    private final transient ApplicationError error;

    public ApplicationException(ApplicationError error) {
        super(error.getCode());
        this.error = error;
        this.fullErrorDescription = error.getDescription();
    }

    public ApplicationException(ApplicationError error, String additionalParam) {
        super(error.getCode());
        this.error = error;
        this.fullErrorDescription = error.getDescription() + ": " + additionalParam;
    }
}
