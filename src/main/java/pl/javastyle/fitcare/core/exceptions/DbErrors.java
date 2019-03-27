package pl.javastyle.fitcare.core.exceptions;

public enum DbErrors implements ApplicationError {
    ITEM_NOT_FOUND("Given item doesn't exist"),
    ITEMS_NOT_FOUND("No items found"),
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
