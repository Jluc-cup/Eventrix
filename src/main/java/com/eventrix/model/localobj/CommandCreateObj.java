package com.eventrix.model.localobj;

import com.eventrix.api.req.CommandCreateReqV1;

public record CommandCreateObj(String logic,
                               String targetService,
                               String description) {
    public CommandCreateObj(CommandCreateReqV1 req) {
        this(req.logic(), req.targetService(), req.description());
    }
}
