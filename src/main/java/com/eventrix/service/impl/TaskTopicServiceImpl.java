package com.eventrix.service.impl;

import com.eventrix.api.req.TaskTopicCreateReqV1;
import com.eventrix.api.req.TaskTopicUpdateActiveReqV1;
import com.eventrix.api.req.TaskTopicUpdateReqV1;
import com.eventrix.base.feature.command.CommandManager;
import com.eventrix.base.feature.command.CommandNames;
import com.eventrix.model.localobj.TaskTopicCreateObj;
import com.eventrix.model.localobj.TaskTopicDeleteObj;
import com.eventrix.model.localobj.TaskTopicUpdateObj;
import com.eventrix.model.localobj.TaskTopicUpdateStatusObj;
import com.eventrix.service.TaskTopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskTopicServiceImpl implements TaskTopicService {

    private final CommandManager commandManager;

    @Override
    public int create(TaskTopicCreateReqV1 req) {
        final TaskTopicCreateObj taskTopicCreateObj = new TaskTopicCreateObj(req);
        return commandManager.executeCommand(CommandNames.TASK_TOPIC_CREATE, taskTopicCreateObj);
    }

    @Override
    public void update(int taskTopicId, TaskTopicUpdateReqV1 req) {
        final TaskTopicUpdateObj taskTopicUpdateObj = new TaskTopicUpdateObj(taskTopicId, req);
        commandManager.executeCommand(CommandNames.TASK_TOPIC_UPDATE, taskTopicUpdateObj);
    }

    @Override
    public void updateActive(int taskTopicId, TaskTopicUpdateActiveReqV1 req) {
        final TaskTopicUpdateStatusObj taskTopicUpdateStatusObj = new TaskTopicUpdateStatusObj(taskTopicId, req);
        commandManager.executeCommand(CommandNames.TASK_TOPIC_UPDATE_STATUS, taskTopicUpdateStatusObj);
    }

    @Override
    public void delete(int taskTopicId) {
        final TaskTopicDeleteObj taskTopicDeleteObj = new TaskTopicDeleteObj(taskTopicId);
        commandManager.executeCommand(CommandNames.TASK_TOPIC_DELETE, taskTopicDeleteObj);
    }
}
