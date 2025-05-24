package com.eventrix.model.localobj;

import com.eventrix.api.req.TaskTopicUpdateActiveReqV1;

public record TaskTopicUpdateStatusObj(boolean isActive) {

    public TaskTopicUpdateStatusObj(TaskTopicUpdateActiveReqV1 req) {
        this(req.isActive());
    }
}
