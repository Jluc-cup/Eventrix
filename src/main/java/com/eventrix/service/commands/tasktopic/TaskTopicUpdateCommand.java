package com.eventrix.service.commands.tasktopic;

import com.eventrix.base.feature.command.Command;
import com.eventrix.base.feature.transaction.TransactionWrapper;
import com.eventrix.dao.TaskTopicDao;
import com.eventrix.model.entity.TaskTopicEntity;
import com.eventrix.model.localobj.TaskTopicUpdateObj;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskTopicUpdateCommand implements Command<TaskTopicUpdateObj, Void> {

    private final TaskTopicDao taskTopicDao;

    private final TransactionWrapper transaction;

    @Override
    public Void execute(TaskTopicUpdateObj obj) {
        transaction.executeWithoutResult(() -> updateT(obj));
        return null;
    }


    private void updateT(TaskTopicUpdateObj obj) {
        TaskTopicEntity taskTopicEntity = taskTopicDao.findById(obj.id());
        taskTopicEntity = taskTopicEntity.update(obj);
        taskTopicDao.save(taskTopicEntity);
    }
}