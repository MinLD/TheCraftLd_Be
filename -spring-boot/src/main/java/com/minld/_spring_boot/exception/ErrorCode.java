package com.minld._spring_boot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION("UNCATEGORIZED_ERROR", 9999, HttpStatus.INTERNAL_SERVER_ERROR),
    NAME_INVALID("Name must be at lest {min} characters", 1000, HttpStatus.BAD_REQUEST),
    EMAIL_EXISTED("User existed", 1001, HttpStatus.BAD_REQUEST),
    EMAIL_INVALID("Email invalid", 1002, HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID("Password must be at lest {min} characters", 1003, HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND("User not found", 1004, HttpStatus.NOT_FOUND),
    UNAUTHENTICATED("Unauthenticated", 1005, HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED("You do not have permission", 1006, HttpStatus.FORBIDDEN),
    INVALID_DOB("Invalid date of birth {min}", 1007, HttpStatus.BAD_REQUEST),
    INVALID_KEY("Uncategorized error", 1008, HttpStatus.BAD_REQUEST),
    PROFILE_NOT_FOUND("Profile not found", 1009, HttpStatus.NOT_FOUND),
    EMAIL_SENDING_FAILED("Failed to send email", 1010, HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_CODE("Invalid code", 1011, HttpStatus.BAD_REQUEST),
    UPLOAD_FAILED("Upload failed", 1012, HttpStatus.BAD_REQUEST),
    SELLER_EXIST("Seller existed", 1013, HttpStatus.BAD_REQUEST),
    SELLER_NOT_FOUND("Seller not found", 1014, HttpStatus.BAD_REQUEST),
    CATEGORY_EXISTED("Category existed", 1015, HttpStatus.BAD_REQUEST),
    CATEGORY_NOT_FOUND("Category not found", 1016, HttpStatus.NOT_FOUND),
    ROLES_NOT_FOUND("Roles not found", 1017, HttpStatus.NOT_FOUND),
    ADDRESS_NOT_FOUND("Address not found", 1018, HttpStatus.NOT_FOUND),
    PRODUCT_NOT_FOUND("Product not found", 1019, HttpStatus.NOT_FOUND),
    ATTRIBUTE_NOT_FOUND("Attribute not found", 1020, HttpStatus.NOT_FOUND),
    DESCRIPTION_INVALID("Description must be at lest {min} characters", 1021, HttpStatus.BAD_REQUEST),
    PRICE_INVALID("Price must be at lest {min} characters", 1022, HttpStatus.BAD_REQUEST),
    QUANTITY_INVALID("Quantity must be at lest {min} characters", 1023, HttpStatus.BAD_REQUEST),
    DISCOUNT_INVALID("Discount must be at lest {min} characters", 1024, HttpStatus.BAD_REQUEST),
    TRADEMARK_INVALID("Trademark must be at lest {min} characters", 1025, HttpStatus.BAD_REQUEST),
    ORIGIN_INVALID("Origin must be at lest {min} characters", 1026, HttpStatus.BAD_REQUEST),
    STYLE_INVALID("Style must be at lest {min} characters", 1027, HttpStatus.BAD_REQUEST),
    MATERIAL_INVALID("Material must be at lest {min} characters", 1028, HttpStatus.BAD_REQUEST),
    MEDIA_NOT_FOUND("Media not found", 1029, HttpStatus.NOT_FOUND),
    INSUFFICIENT_STOCK("Insufficient stock", 1030, HttpStatus.BAD_REQUEST),
    ATTRIBUTES_VALUE_NOT_FOUND("Attributes value not found", 1031, HttpStatus.NOT_FOUND),
    ORDER_NOT_FOUND("Order value not found", 1032, HttpStatus.NOT_FOUND),
    CART_ITEM_NOT_FOUND("Cart item not found", 1033, HttpStatus.NOT_FOUND),
    ;

    ErrorCode(String message, int code, HttpStatusCode statusCode) {
        this.message = message;
        this.code = code;
        this.statusCode = statusCode;
    }

    private int code;
    private String message;
    private HttpStatusCode statusCode;
}
