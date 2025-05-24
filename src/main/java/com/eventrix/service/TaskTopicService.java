package com.eventrix.service;

import com.eventrix.api.req.TaskTopicCreateReqV1;
import com.eventrix.api.req.TaskTopicUpdateActiveReqV1;
import com.eventrix.api.req.TaskTopicUpdateReqV1;

public interface TaskTopicService {

    int create(TaskTopicCreateReqV1 req);

    void update(int taskTopicId, TaskTopicUpdateReqV1 req);

    void updateActive(int taskTopicId, TaskTopicUpdateActiveReqV1 req);
}
