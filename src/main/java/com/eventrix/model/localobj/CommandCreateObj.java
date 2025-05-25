package com.eventrix.model.localobj;

import com.eventrix.api.req.CommandCreateReqV1;
import com.eventrix.service.strategy.StrategyContextInterface;

public record CommandCreateObj(String logic,
                               String targetService,
                               String description) implements StrategyContextInterface {
    public CommandCreateObj(CommandCreateReqV1 req) {
        this(req.logic(), req.targetService(), req.description());
    }
}
