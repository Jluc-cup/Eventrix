package com.eventrix.model.localobj;

import com.eventrix.api.req.TaskTopicUpdateActiveReqV1;

public record TaskTopicUpdateStatusObj(int id,
                                       boolean isActive) {

    public TaskTopicUpdateStatusObj(int taskTopicId, TaskTopicUpdateActiveReqV1 req) {
        this(taskTopicId, req.isActive());
    }
}
