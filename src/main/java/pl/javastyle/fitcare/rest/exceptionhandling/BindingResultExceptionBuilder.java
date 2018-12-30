package pl.javastyle.fitcare.rest.exceptionhandling;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import pl.javastyle.fitcare.exceptions.ApplicationException;
import pl.javastyle.fitcare.exceptions.ValidationErrors;

public class BindingResultExceptionBuilder {
    private BindingResult bindingResult;

    public BindingResultExceptionBuilder(BindingResult result) {
        this.bindingResult = result;
    }

    public void buildException() {
        String errorMessage = buildMessageFromBindingResult(this.bindingResult);
        ValidationErrors.NOT_VALID.setDescription(errorMessage);

        throw new ApplicationException(ValidationErrors.NOT_VALID);
    }

    private String buildMessageFromBindingResult(BindingResult result) {
        StringBuilder allErrorMessages = new StringBuilder();

        for (ObjectError error : result.getAllErrors()) {
            allErrorMessages
                    .append("Given field '")
                    .append(((FieldError)error).getField())
                    .append("' ")
                    .append(error.getDefaultMessage())
                    .append("\n\n");
        }

        return allErrorMessages.toString();
    }
}
