package pl.javastyle.fitcare.exceptions;

import lombok.Getter;

public class ApplicationException extends RuntimeException {

    @Getter
    private final transient ApplicationError error;

    public ApplicationException(ApplicationError message) {
        super(message.getCode());
        this.error = message;
    }

    public ApplicationException(ApplicationError message, Throwable cause) {
        super(message.getCode(), cause);
        this.error = message;
    }

    public ApplicationException(ApplicationError message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message.getCode(), cause, enableSuppression, writableStackTrace);
        this.error = message;
    }
}
