package com.eventrix.api.req;

public record TaskTopicCreateReqV1(String name,
                                   String description,
                                   Long priority) {
}
