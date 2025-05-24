package com.eventrix.api.req;

public record TaskTopicUpdateReqV1(String name,
                                   String description,
                                   Long priority) {
}
