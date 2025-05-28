package com.eventrix.service.impl;

import com.eventrix.api.req.TaskTopicCreateReqV1;
import com.eventrix.api.req.TaskTopicUpdateActiveReqV1;
import com.eventrix.api.req.TaskTopicUpdateReqV1;
import com.eventrix.base.feature.command.Command;
import com.eventrix.dao.TaskTopicDao;
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

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TaskTopicServiceImpl implements TaskTopicService {

    private final Map<Class<?>, Command<?, ?>> commandRegistry = new HashMap<>();

    public TaskTopicServiceImpl(TaskTopicCreateCommand createCommand,
                                TaskTopicUpdateCommand updateCommand, TaskTopicUpdateStatusCommand updateStatusCommand,
                                TaskTopicDeleteCommand deleteCommand) {
        registerCommand(TaskTopicCreateObj.class, createCommand);
        registerCommand(TaskTopicUpdateObj.class, updateCommand);
        registerCommand(TaskTopicUpdateStatusObj.class, updateStatusCommand);
        registerCommand(TaskTopicDeleteObj.class, deleteCommand);
    }

    private <T, R> void registerCommand(Class<T> clazz, Command<T, R> command) {
        commandRegistry.put(clazz, command);
    }

    @SuppressWarnings("unchecked")
    private <T, R> R executeOperation(T obj) {
        Command<T, R> command = (Command<T, R>) commandRegistry.get(obj.getClass());
        if (command == null) {
            throw new IllegalArgumentException("No command registered for " + obj.getClass());
        }
        return command.execute(obj);
    }

    @Override
    public int create(TaskTopicCreateReqV1 req) {
        final TaskTopicCreateObj taskTopicCreateObj = new TaskTopicCreateObj(req);
        return executeOperation(taskTopicCreateObj);
    }

    @Override
    public void update(int taskTopicId, TaskTopicUpdateReqV1 req) {
        final TaskTopicUpdateObj taskTopicUpdateObj = new TaskTopicUpdateObj(taskTopicId, req);
        executeOperation(taskTopicUpdateObj);
    }

    @Override
    public void updateActive(int taskTopicId, TaskTopicUpdateActiveReqV1 req) {
        final TaskTopicUpdateStatusObj taskTopicUpdateStatusObj = new TaskTopicUpdateStatusObj(taskTopicId, req);
        executeOperation(taskTopicUpdateStatusObj);
    }

    @Override
    public void delete(int taskTopicId) {
        final TaskTopicDeleteObj taskTopicDeleteObj = new TaskTopicDeleteObj(taskTopicId);
        executeOperation(taskTopicDeleteObj);
    }
}
