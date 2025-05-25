package com.eventrix.base.errors;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionType {
    BAD_REQUEST("bad request", HttpStatus.BAD_REQUEST),
    NOT_FOUND("not fount", HttpStatus.NOT_FOUND),
    INTERNAL_SERVER_ERROR("internal server error", HttpStatus.INTERNAL_SERVER_ERROR),;

    private final String message;
    private final HttpStatus status;

    ExceptionType(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}