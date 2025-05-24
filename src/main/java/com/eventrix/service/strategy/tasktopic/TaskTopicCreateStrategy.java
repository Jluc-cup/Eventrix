package com.eventrix.service.strategy.tasktopic;


import com.eventrix.dao.TaskTopicDao;
import com.eventrix.model.entity.TaskTopicEntity;
import com.eventrix.model.localobj.TaskTopicCreateObj;
import com.eventrix.service.strategy.OperationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskTopicCreateStrategy implements OperationStrategy<TaskTopicCreateObj, Integer> {


    private final TaskTopicDao taskTopicDao;


    @Override
    public Integer execute(TaskTopicCreateObj obj) {
        final TaskTopicEntity taskTopicEntity = new TaskTopicEntity(obj);
        taskTopicDao.save(taskTopicEntity);
        return taskTopicEntity.getId();
    }
}