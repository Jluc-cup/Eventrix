package com.eventrix.base.util;

import java.time.Instant;

public class DateTimeUtil {

    private static final Instant currentTime = Instant.MIN;

    public static Instant getCurrentTime() {
        if (currentTime == Instant.MIN) {
            return Instant.now();
        }
        return currentTime;
    }
}
