package pl.javastyle.fitcare.exceptions;

import lombok.Getter;
import lombok.Setter;

public class ApplicationException extends RuntimeException {

    @Getter @Setter
    private ApplicationError error;

    public ApplicationException(ApplicationError message) {
        super(message.getCode());
        setError(message);
    }

    public ApplicationException(ApplicationError message, Throwable cause) {
        super(message.getCode(), cause);
        setError(message);
    }

    public ApplicationException(ApplicationError message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message.getCode(), cause, enableSuppression, writableStackTrace);
        setError(message);
    }
}
