package pl.javastyle.fitcare.core.rest;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import pl.javastyle.fitcare.core.exceptions.ApplicationException;
import pl.javastyle.fitcare.core.exceptions.ValidationErrors;

public class BindingResultExceptionBuilder {

    private final BindingResult bindingResult;

    public BindingResultExceptionBuilder(BindingResult result) {
        this.bindingResult = result;
    }

    public void buildException() {
        String errorMessage = buildMessageFromBindingResult();
        ValidationErrors.NOT_VALID.setDescription(errorMessage);

        throw new ApplicationException(ValidationErrors.NOT_VALID);
    }

    private String buildMessageFromBindingResult() {
        StringBuilder allErrorMessages = new StringBuilder();
        for (ObjectError error : bindingResult.getAllErrors()) {
            allErrorMessages
                    .append("Given field '")
                    .append(((FieldError) error).getField())
                    .append("' ")
                    .append(error.getDefaultMessage())
                    .append("\n\n");
        }

        return allErrorMessages.toString();
    }
}
