package pl.javastyle.fitcare.exceptions;

public enum DbErrors implements ApplicationError {
    CATEGORY_NOT_FOUND("Category not found in database"),
    PRODUCT_NOT_FOUND("Product not found in database"),
    DUPLICATED_PRODUCT_NAME("Product name already exist in database"),
    DUPLICATED_CATEGORY_NAME("Category name already exist in database");


    private final String description;

    DbErrors(String s) {
        this.description = s;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getCode() {
        return this.toString();
    }
}
