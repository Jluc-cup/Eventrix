package com.eventrix.base.util;

import com.eventrix.base.errors.ExceptionFactory;
import com.eventrix.base.errors.ExceptionType;

public class ObjectUtil {

    public static void checkNotNull(Object obj) {
        if (obj == null) {
            throw ExceptionFactory.create(ExceptionType.NOT_FOUND);
        }
    }
}
