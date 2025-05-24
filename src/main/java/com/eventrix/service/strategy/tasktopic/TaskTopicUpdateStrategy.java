package com.eventrix.service.strategy.tasktopic;

import com.eventrix.dao.TaskTopicDao;
import com.eventrix.model.entity.TaskTopicEntity;
import com.eventrix.model.localobj.TaskTopicUpdateObj;
import com.eventrix.service.strategy.OperationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskTopicUpdateStrategy implements OperationStrategy<TaskTopicUpdateObj, Void> {

    private final TaskTopicDao taskTopicDao;

    @Override
    public Void execute(TaskTopicUpdateObj request) {
        TaskTopicEntity taskTopicEntity = taskTopicDao.findById(request.taskTopicId());
        taskTopicEntity = taskTopicEntity.update(request);
        taskTopicDao.save(taskTopicEntity);
        return null;
    }
}