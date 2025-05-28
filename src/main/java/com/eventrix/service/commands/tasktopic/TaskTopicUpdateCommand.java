package com.eventrix.service.commands.tasktopic;

import com.eventrix.base.feature.command.Command;
import com.eventrix.base.feature.command.CommandDefinition;
import com.eventrix.dao.TaskTopicDao;
import com.eventrix.model.entity.TaskTopicEntity;
import com.eventrix.model.localobj.TaskTopicUpdateObj;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@CommandDefinition(bean = TaskTopicUpdateCommand.class,
        contextType = TaskTopicUpdateObj.class,
        returnType = Void.class)
public class TaskTopicUpdateCommand implements Command<TaskTopicUpdateObj, Void> {

    private final TaskTopicDao taskTopicDao;

    @Override
    public Void execute(TaskTopicUpdateObj obj) {
        TaskTopicEntity taskTopicEntity = taskTopicDao.findById(obj.id());
        taskTopicEntity = taskTopicEntity.update(obj);
        taskTopicDao.save(taskTopicEntity);
        return null;
    }

}