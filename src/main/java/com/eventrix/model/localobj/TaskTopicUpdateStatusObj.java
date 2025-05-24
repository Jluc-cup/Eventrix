package com.eventrix.model.localobj;

import com.eventrix.api.req.TaskTopicUpdateActiveReqV1;
import com.eventrix.service.strategy.StrategyContextInterface;

public record TaskTopicUpdateStatusObj(boolean isActive) implements StrategyContextInterface {

    public TaskTopicUpdateStatusObj(TaskTopicUpdateActiveReqV1 req) {
        this(req.isActive());
    }
}
