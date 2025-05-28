package com.eventrix.service.commands.tasktopic;

import com.eventrix.base.feature.command.Command;
import com.eventrix.base.feature.command.CommandDefinition;
import com.eventrix.dao.TaskTopicDao;
import com.eventrix.model.entity.TaskTopicEntity;
import com.eventrix.model.localobj.TaskTopicDeleteObj;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@CommandDefinition(bean = TaskTopicDeleteCommand.class,
        contextType = TaskTopicDeleteObj.class,
        returnType = Void.class)
public class TaskTopicDeleteCommand implements Command<TaskTopicDeleteObj, Void> {

    private final TaskTopicDao taskTopicDao;

    @Override
    public Void execute(TaskTopicDeleteObj obj) {
        TaskTopicEntity taskTopicEntity = taskTopicDao.findById(obj.id());
        taskTopicEntity = taskTopicEntity.delete();
        taskTopicDao.save(taskTopicEntity);
        return null;
    }
}
