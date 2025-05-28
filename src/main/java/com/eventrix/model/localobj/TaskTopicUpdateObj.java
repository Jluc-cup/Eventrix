package com.eventrix.model.localobj;

import com.eventrix.api.req.TaskTopicUpdateReqV1;

public record TaskTopicUpdateObj(int id,
                                 String name,
                                 String description,
                                 Long priority) {

    public TaskTopicUpdateObj(int taskTopicId, TaskTopicUpdateReqV1 req) {
        this(taskTopicId, req.name(), req.description(), req.priority());
    }
}
