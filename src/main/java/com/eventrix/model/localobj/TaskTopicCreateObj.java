package com.eventrix.model.localobj;


import com.eventrix.api.req.TaskTopicCreateReqV1;

public record TaskTopicCreateObj(String name,
                                 String description,
                                 Long priority) {

    public TaskTopicCreateObj(TaskTopicCreateReqV1 req) {
        this(req.name(), req.description(), req.priority());
    }
}
