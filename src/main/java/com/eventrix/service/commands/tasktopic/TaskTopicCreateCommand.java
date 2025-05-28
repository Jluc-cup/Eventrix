package com.eventrix.service.commands.tasktopic;


import com.eventrix.base.feature.command.Command;
import com.eventrix.base.feature.transaction.TransactionWrapper;
import com.eventrix.dao.TaskTopicDao;
import com.eventrix.model.entity.TaskTopicEntity;
import com.eventrix.model.localobj.TaskTopicCreateObj;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskTopicCreateCommand implements Command<TaskTopicCreateObj, Integer> {


    private final TaskTopicDao taskTopicDao;

    private final TransactionWrapper transaction;

    @Override
    public Integer execute(TaskTopicCreateObj obj) {
        final TaskTopicEntity taskTopicEntity = transaction.execute(() -> createT(obj));
        return taskTopicEntity.getId();
    }

    private TaskTopicEntity createT(TaskTopicCreateObj obj) {
        final TaskTopicEntity taskTopicEntity = new TaskTopicEntity(obj);
        taskTopicDao.save(taskTopicEntity);
        return taskTopicEntity;
    }
}