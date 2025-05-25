package com.eventrix.api.req;


import java.time.Instant;
import java.util.List;

public record TaskCreateReqV1(
        int topicId,
        String name,
        String createdBy,
        String logic,
        String parameters,
        long priority,
        Long delayMs,
        Long periodMs,
        Instant startTime,
        Long timeoutMs,
        int retryCount,
        List<TaskCreateReqV1> dependencies
) {


}