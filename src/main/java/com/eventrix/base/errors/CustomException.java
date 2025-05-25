package com.eventrix.base.errors;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomException extends RuntimeException {
    private final ExceptionType type;
    private final String customDetails;

    public CustomException(ExceptionType type) {
        super(type.getMessage());
        this.type = type;
        this.customDetails = null;
    }

    public CustomException(ExceptionType type, String customDetails) {
        super(type.getMessage() + (customDetails != null ? ": " + customDetails : ""));
        this.type = type;
        this.customDetails = customDetails;
    }

    public CustomException(ExceptionType type, Throwable cause) {
        super(type.getMessage(), cause);
        this.type = type;
        this.customDetails = null;
    }

    public CustomException(ExceptionType type, String customDetails, Throwable cause) {
        super(type.getMessage() + (customDetails != null ? ": " + customDetails : ""), cause);
        this.type = type;
        this.customDetails = customDetails;
    }
}