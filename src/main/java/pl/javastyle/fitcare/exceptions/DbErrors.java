package pl.javastyle.fitcare.exceptions;

public enum DbErrors implements ApplicationError {
    PRODUCT_NOT_FOUND("Given product doesn't exist"),
    PRODUCTS_NOT_FOUND("Products not found"),
    CATEGORY_NOT_FOUND("Given category doesn't exist"),
    CATEGORIES_NOT_FOUND("Categories not found"),
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
