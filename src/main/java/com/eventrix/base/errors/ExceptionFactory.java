package com.eventrix.base.errors;

import com.eventrix.base.util.JsonUtil;

import java.util.Map;

public final class ExceptionFactory {
    public static CustomException create(ExceptionType type) {
        return new CustomException(type);
    }

    public static CustomException create(ExceptionType type, String details) {
        return new CustomException(type, details);
    }

    public static CustomException create(ExceptionType type, String details, Throwable cause) {
        return new CustomException(type, details, cause);
    }

    public static CustomException create(ExceptionType type, Throwable cause) {
        return new CustomException(type, cause);
    }

    public static CustomException create(ExceptionType type, Map<String, Object> mapDetails, Throwable cause) {
        try {
            String details = JsonUtil.toJson(mapDetails);
            return new CustomException(type, details, cause);
        } catch (Exception e) {
            return new CustomException(type, "Failed to serialize details: " + e.getMessage(), cause);
        }
    }
}