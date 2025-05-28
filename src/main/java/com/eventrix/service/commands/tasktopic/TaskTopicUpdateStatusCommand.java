package com.eventrix.service.commands.tasktopic;

import com.eventrix.base.feature.command.Command;
import com.eventrix.base.feature.command.CommandDefinition;
import com.eventrix.base.feature.command.CommandNames;
import com.eventrix.base.feature.transaction.TransactionWrapper;
import com.eventrix.dao.TaskTopicDao;
import com.eventrix.model.entity.TaskTopicEntity;
import com.eventrix.model.localobj.TaskTopicUpdateObj;
import com.eventrix.model.localobj.TaskTopicUpdateStatusObj;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@CommandDefinition(name = CommandNames.TASK_TOPIC_UPDATE_STATUS,
        contextType = TaskTopicUpdateStatusObj.class,
        returnType = Void.class)
public class TaskTopicUpdateStatusCommand implements Command<TaskTopicUpdateStatusObj, Void> {

    private final TaskTopicDao taskTopicDao;

    private final TransactionWrapper transaction;

    @Override
    public Void execute(TaskTopicUpdateStatusObj obj) {
        transaction.executeWithoutResult(() -> updateStatusT(obj));
        return null;
    }


    private void updateStatusT(TaskTopicUpdateStatusObj obj) {
        TaskTopicEntity taskTopicEntity = taskTopicDao.findById(obj.id());
        taskTopicEntity = taskTopicEntity.updateStatus(obj);
        taskTopicDao.save(taskTopicEntity);
    }

}
