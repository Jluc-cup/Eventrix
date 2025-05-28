package com.eventrix.service.impl;

import com.eventrix.api.req.TaskTopicCreateReqV1;
import com.eventrix.api.req.TaskTopicUpdateActiveReqV1;
import com.eventrix.api.req.TaskTopicUpdateReqV1;
import com.eventrix.base.feature.command.CommandManager;

import com.eventrix.base.feature.transaction.TransactionWrapper;
import com.eventrix.model.localobj.TaskTopicCreateObj;
import com.eventrix.model.localobj.TaskTopicDeleteObj;
import com.eventrix.model.localobj.TaskTopicUpdateObj;
import com.eventrix.model.localobj.TaskTopicUpdateStatusObj;
import com.eventrix.service.TaskTopicService;
import com.eventrix.service.commands.tasktopic.TaskTopicCreateCommand;
import com.eventrix.service.commands.tasktopic.TaskTopicDeleteCommand;
import com.eventrix.service.commands.tasktopic.TaskTopicUpdateCommand;
import com.eventrix.service.commands.tasktopic.TaskTopicUpdateStatusCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskTopicServiceImpl implements TaskTopicService {

    private final CommandManager commandManager;

    private final TransactionWrapper transaction;


    @Override
    public int create(TaskTopicCreateReqV1 req) {
        final TaskTopicCreateObj taskTopicCreateObj = new TaskTopicCreateObj(req);
        return transaction.execute(() -> createTaskTopicT(taskTopicCreateObj));
    }

    private int createTaskTopicT(TaskTopicCreateObj taskTopicCreateObj) {
        return commandManager.executeCommand(TaskTopicCreateCommand.class, taskTopicCreateObj);
    }


    @Override
    public void update(int taskTopicId, TaskTopicUpdateReqV1 req) {
        final TaskTopicUpdateObj taskTopicUpdateObj = new TaskTopicUpdateObj(taskTopicId, req);
        transaction.executeWithoutResult(() -> updateT(taskTopicUpdateObj));
    }

    private void updateT(TaskTopicUpdateObj taskTopicUpdateObj) {
        commandManager.executeCommand(TaskTopicUpdateCommand.class, taskTopicUpdateObj);
    }


    @Override
    public void updateActive(int taskTopicId, TaskTopicUpdateActiveReqV1 req) {
        final TaskTopicUpdateStatusObj taskTopicUpdateStatusObj = new TaskTopicUpdateStatusObj(taskTopicId, req);
        transaction.executeWithoutResult(() -> updateStatusT(taskTopicUpdateStatusObj));
    }

    private void updateStatusT(TaskTopicUpdateStatusObj taskTopicUpdateStatusObj) {
        commandManager.executeCommand(TaskTopicUpdateStatusCommand.class, taskTopicUpdateStatusObj);
    }


    @Override
    public void delete(int taskTopicId) {
        final TaskTopicDeleteObj taskTopicDeleteObj = new TaskTopicDeleteObj(taskTopicId);
        transaction.executeWithoutResult(() -> deleteT(taskTopicDeleteObj));
    }


    private void deleteT(TaskTopicDeleteObj taskTopicDeleteObj) {
        commandManager.executeCommand(TaskTopicDeleteCommand.class, taskTopicDeleteObj);
    }
}
