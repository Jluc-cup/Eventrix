package com.eventrix.model.localobj;

import com.eventrix.api.req.TaskTopicUpdateReqV1;
import com.eventrix.service.strategy.StrategyContextInterface;

public record TaskTopicUpdateObj(int id,
                                 String name,
                                 String description,
                                 Long priority) implements StrategyContextInterface {

    public TaskTopicUpdateObj(int taskTopicId, TaskTopicUpdateReqV1 req) {
        this(taskTopicId, req.name(), req.description(), req.priority());
    }
}
