package com.eventrix.base.swagger.annotation;

public interface SwaggerDoc {
    String summary();

    String description();

    String responseCode();

    String[] errorCodes();
}