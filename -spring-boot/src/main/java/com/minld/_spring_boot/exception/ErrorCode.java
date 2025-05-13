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
