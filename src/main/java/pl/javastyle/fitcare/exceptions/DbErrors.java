package pl.javastyle.fitcare.exceptions;

public enum DbErrors implements ApplicationError {
    PRODUCT_NOT_FOUND("Given product doesn't exist"),
    CATEGORY_NOT_FOUND("Given category doesn't exist"),
    DUPLICATED_PRODUCT_NAME("Given product already exist"),
    DUPLICATED_CATEGORY_NAME("Given category already exist");

    private final String description;

    DbErrors(String description) {
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
