package pl.javastyle.fitcare.core.exceptions;

import lombok.Setter;

public enum ValidationErrors implements ApplicationError {

    NOT_VALID("NOT_VALID");

    @Setter
    private String description;

    ValidationErrors(String description) {
        this.description = description;
    }


    @Override
    public String getCode() {
        return this.toString();
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
