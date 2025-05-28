package com.eventrix.service.commands.tasktopic;

import com.eventrix.base.feature.command.Command;
import com.eventrix.base.feature.transaction.TransactionWrapper;
import com.eventrix.dao.TaskTopicDao;
import com.eventrix.model.entity.TaskTopicEntity;
import com.eventrix.model.localobj.TaskTopicDeleteObj;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskTopicDeleteCommand implements Command<TaskTopicDeleteObj, Void> {

    private final TaskTopicDao taskTopicDao;

    private final TransactionWrapper transaction;

    @Override
    public Void execute(TaskTopicDeleteObj obj) {
        transaction.executeWithoutResult(() -> deleteT(obj));
        return null;
    }

    private void deleteT(TaskTopicDeleteObj obj) {
        TaskTopicEntity taskTopicEntity = taskTopicDao.findById(obj.id());
        taskTopicEntity = taskTopicEntity.delete();
        taskTopicDao.save(taskTopicEntity);
    }
}
