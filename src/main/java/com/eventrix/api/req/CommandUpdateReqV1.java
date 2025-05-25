package com.eventrix.api.req;

public record CommandUpdateReqV1(String logic,
                                 String targetService,
                                 String description) {
}
