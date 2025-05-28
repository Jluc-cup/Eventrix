package com.eventrix.service.commands.tasktopic;

import com.eventrix.base.feature.command.Command;
import com.eventrix.base.feature.command.CommandDefinition;
import com.eventrix.dao.TaskTopicDao;
import com.eventrix.model.entity.TaskTopicEntity;
import com.eventrix.model.localobj.TaskTopicUpdateStatusObj;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@CommandDefinition(bean = TaskTopicUpdateStatusCommand.class,
        contextType = TaskTopicUpdateStatusObj.class,
        returnType = Void.class)
public class TaskTopicUpdateStatusCommand implements Command<TaskTopicUpdateStatusObj, Void> {

    private final TaskTopicDao taskTopicDao;

    @Override
    public Void execute(TaskTopicUpdateStatusObj obj) {
        TaskTopicEntity taskTopicEntity = taskTopicDao.findById(obj.id());
        taskTopicEntity = taskTopicEntity.updateStatus(obj);
        taskTopicDao.save(taskTopicEntity);
        return null;
    }


}
