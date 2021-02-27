package pl.javastyle.fitcare.core.exceptions;

public enum DbErrors implements ApplicationError {

    ITEM_NOT_FOUND("Given item doesn't exist"),
    CATEGORY_NOT_FOUND("Given category does not exist");

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
