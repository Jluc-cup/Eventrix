package com.eventrix.api.req;

public record CommandCreateReqV1(String logic,
                                 String targetService,
                                 String description) {
}
