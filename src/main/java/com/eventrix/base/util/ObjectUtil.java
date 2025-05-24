package com.eventrix.base.util;

public class ObjectUtil {

    public static void checkNotNull(Object obj) {
        if (obj == null) {
            throw new NullPointerException(); //TODO
        }
    }
}
