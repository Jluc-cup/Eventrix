package com.eventrix.service.commands.tasktopic;

import com.eventrix.base.feature.command.Command;
import com.eventrix.base.feature.transaction.TransactionWrapper;
import com.eventrix.dao.TaskTopicDao;
import com.eventrix.model.entity.TaskTopicEntity;
import com.eventrix.model.localobj.TaskTopicUpdateStatusObj;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
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
