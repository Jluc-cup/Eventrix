package com.eventrix.model.localobj;

import com.eventrix.api.req.TaskTopicUpdateReqV1;

public record TaskTopicUpdateObj(String name,
                                 String description,
                                 Long priority) {

    public TaskTopicUpdateObj(TaskTopicUpdateReqV1 req) {
        this(req.name(), req.description(), req.priority());
    }
}
