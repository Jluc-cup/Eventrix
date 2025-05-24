package com.eventrix.model.localobj;


import com.eventrix.api.req.TaskTopicCreateReqV1;
import com.eventrix.service.strategy.StrategyContextInterface;

public record TaskTopicCreateObj(String name,
                                 String description,
                                 Long priority) implements StrategyContextInterface {

    public TaskTopicCreateObj(TaskTopicCreateReqV1 req) {
        this(req.name(), req.description(), req.priority());
    }
}
