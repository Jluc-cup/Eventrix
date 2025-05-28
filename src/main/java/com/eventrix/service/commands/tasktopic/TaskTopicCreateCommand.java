package com.eventrix.service.commands.tasktopic;


import com.eventrix.base.feature.command.Command;
import com.eventrix.base.feature.command.CommandDefinition;

import com.eventrix.dao.TaskTopicDao;
import com.eventrix.model.entity.TaskTopicEntity;
import com.eventrix.model.localobj.TaskTopicCreateObj;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@CommandDefinition(bean = TaskTopicCreateCommand.class,
        contextType = TaskTopicCreateObj.class,
        returnType = Integer.class)
public class TaskTopicCreateCommand implements Command<TaskTopicCreateObj, Integer> {

    private final TaskTopicDao taskTopicDao;

    @Override
    public Integer execute(TaskTopicCreateObj obj) {
        final TaskTopicEntity taskTopicEntity = new TaskTopicEntity(obj);
        taskTopicDao.save(taskTopicEntity);
        return taskTopicEntity.getId();
    }

}