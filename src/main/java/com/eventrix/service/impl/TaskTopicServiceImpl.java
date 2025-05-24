package com.eventrix.service.impl;

import com.eventrix.api.req.TaskTopicCreateReqV1;
import com.eventrix.api.req.TaskTopicUpdateActiveReqV1;
import com.eventrix.api.req.TaskTopicUpdateReqV1;
import com.eventrix.dao.TaskTopicDao;
import com.eventrix.model.entity.TaskTopicEntity;
import com.eventrix.model.localobj.TaskTopicCreateObj;
import com.eventrix.model.localobj.TaskTopicUpdateObj;
import com.eventrix.model.localobj.TaskTopicUpdateStatusObj;
import com.eventrix.base.feature.AbstractService;
import com.eventrix.service.TaskTopicService;
import com.eventrix.service.strategy.tasktopic.TaskTopicCreateStrategy;
import com.eventrix.service.strategy.tasktopic.TaskTopicUpdateStrategy;
import org.springframework.stereotype.Service;

@Service
public class TaskTopicServiceImpl extends AbstractService implements TaskTopicService {

    private final TaskTopicDao taskTopicDao;

    public TaskTopicServiceImpl(TaskTopicDao taskTopicDao) {
        this.taskTopicDao = taskTopicDao;
        registerStrategy(TaskTopicCreateObj.class, new TaskTopicCreateStrategy(taskTopicDao));
        registerStrategy(TaskTopicUpdateObj.class, new TaskTopicUpdateStrategy(taskTopicDao));
    }


    @Override
    public int create(TaskTopicCreateReqV1 req) {
        final TaskTopicCreateObj taskTopicCreateObj = new TaskTopicCreateObj(req);
        return executeOperation(taskTopicCreateObj);
    }


    @Override
    public void update(int taskTopicId, TaskTopicUpdateReqV1 req) {
        final TaskTopicUpdateObj taskTopicUpdateObj = new TaskTopicUpdateObj(taskTopicId, req);
        executeOperation(taskTopicUpdateObj);
    }


    @Override
    public void updateActive(int taskTopicId, TaskTopicUpdateActiveReqV1 req) {
        final TaskTopicUpdateStatusObj taskTopicUpdateStatusObj = new TaskTopicUpdateStatusObj(req);
        TaskTopicEntity taskTopicEntity = taskTopicDao.findById(taskTopicId);
        taskTopicEntity = taskTopicEntity.updateStatus(taskTopicUpdateStatusObj);
        taskTopicDao.save(taskTopicEntity);
    }
}
