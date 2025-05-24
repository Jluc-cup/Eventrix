package com.eventrix.service.impl;

import com.eventrix.api.req.TaskTopicCreateReqV1;
import com.eventrix.api.req.TaskTopicUpdateActiveReqV1;
import com.eventrix.api.req.TaskTopicUpdateReqV1;
import com.eventrix.base.feature.AbstractService;
import com.eventrix.base.feature.transaction.TransactionWrapper;
import com.eventrix.dao.TaskTopicDao;
import com.eventrix.model.localobj.TaskTopicCreateObj;
import com.eventrix.model.localobj.TaskTopicDeleteObj;
import com.eventrix.model.localobj.TaskTopicUpdateObj;
import com.eventrix.model.localobj.TaskTopicUpdateStatusObj;
import com.eventrix.service.TaskTopicService;
import com.eventrix.service.strategy.tasktopic.TaskTopicCreateStrategy;
import com.eventrix.service.strategy.tasktopic.TaskTopicDeleteStrategy;
import com.eventrix.service.strategy.tasktopic.TaskTopicUpdateStatusStrategy;
import com.eventrix.service.strategy.tasktopic.TaskTopicUpdateStrategy;
import org.springframework.stereotype.Service;

@Service
public class TaskTopicServiceImpl extends AbstractService implements TaskTopicService {

    public TaskTopicServiceImpl(TaskTopicDao taskTopicDao, TransactionWrapper transaction) {
        registerStrategy(TaskTopicCreateObj.class,
                new TaskTopicCreateStrategy(taskTopicDao, transaction));
        registerStrategy(TaskTopicUpdateObj.class,
                new TaskTopicUpdateStrategy(taskTopicDao, transaction));
        registerStrategy(TaskTopicUpdateStatusObj.class,
                new TaskTopicUpdateStatusStrategy(taskTopicDao, transaction));
        registerStrategy(TaskTopicDeleteObj.class,
                new TaskTopicDeleteStrategy(taskTopicDao, transaction));
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
        final TaskTopicUpdateStatusObj taskTopicUpdateStatusObj = new TaskTopicUpdateStatusObj(taskTopicId, req);
        executeOperation(taskTopicUpdateStatusObj);
    }

    @Override
    public void delete(int taskTopicId) {
        final TaskTopicDeleteObj taskTopicDeleteObj = new TaskTopicDeleteObj(taskTopicId);
        executeOperation(taskTopicDeleteObj);
    }
}
