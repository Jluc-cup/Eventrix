package com.eventrix.model.localobj;

import com.eventrix.api.req.TaskTopicUpdateActiveReqV1;
import com.eventrix.service.strategy.StrategyContextInterface;

public record TaskTopicUpdateStatusObj(int id,
                                       boolean isActive) implements StrategyContextInterface {

    public TaskTopicUpdateStatusObj(int taskTopicId, TaskTopicUpdateActiveReqV1 req) {
        this(taskTopicId, req.isActive());
    }
}
