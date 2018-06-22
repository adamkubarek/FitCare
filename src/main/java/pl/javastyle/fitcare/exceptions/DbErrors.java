package pl.javastyle.fitcare.exceptions;

public enum DbErrors implements ApplicationError {
    CATEGORY_NOT_FOUND,
    PRODUCT_NOT_FOUND,
    DUPLICATED_PRODUCT_NAME,
    DUPLICATED_CATEGORY_NAME;


    @Override
    public String getCode() {
        return this.toString();
    }
}
