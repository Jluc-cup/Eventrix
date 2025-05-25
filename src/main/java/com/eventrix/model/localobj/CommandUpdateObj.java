package com.eventrix.model.localobj;

import com.eventrix.api.req.CommandUpdateReqV1;
import com.eventrix.service.strategy.StrategyContextInterface;

public record CommandUpdateObj(int id,
                               String logic,
                               String targetService,
                               String description) implements StrategyContextInterface {

    public CommandUpdateObj(int commandId, CommandUpdateReqV1 req) {
        this(commandId, req.logic(), req.targetService(), req.description());
    }
}
