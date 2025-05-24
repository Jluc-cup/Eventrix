package com.eventrix.service.impl;

import com.eventrix.api.req.TaskTopicCreateReqV1;
import com.eventrix.api.req.TaskTopicUpdateActiveReqV1;
import com.eventrix.api.req.TaskTopicUpdateReqV1;
import com.eventrix.dao.TaskTopicDao;
import com.eventrix.model.entity.TaskTopicEntity;
import com.eventrix.model.localobj.TaskTopicCreateObj;
import com.eventrix.model.localobj.TaskTopicUpdateObj;
import com.eventrix.model.localobj.TaskTopicUpdateStatusObj;
import com.eventrix.service.TaskTopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskTopicServiceImpl implements TaskTopicService {

    private final TaskTopicDao taskTopicDao;

    @Override
    public int create(TaskTopicCreateReqV1 req) {
        final TaskTopicCreateObj taskTopicCreateObj = new TaskTopicCreateObj(req);
        final TaskTopicEntity taskTopicEntity = new TaskTopicEntity(taskTopicCreateObj);
        taskTopicDao.save(taskTopicEntity);
        return taskTopicEntity.getId();
    }


    @Override
    public void update(int taskTopicId, TaskTopicUpdateReqV1 req) {

        final TaskTopicUpdateObj taskTopicUpdateObj = new TaskTopicUpdateObj(req);
        TaskTopicEntity taskTopicEntity = taskTopicDao.findById(taskTopicId);
        taskTopicEntity = taskTopicEntity.update(taskTopicUpdateObj);
        taskTopicDao.save(taskTopicEntity);

    }

    @Override
    public void updateActive(int taskTopicId, TaskTopicUpdateActiveReqV1 req) {
        final TaskTopicUpdateStatusObj taskTopicUpdateStatusObj = new TaskTopicUpdateStatusObj(req);
        TaskTopicEntity taskTopicEntity = taskTopicDao.findById(taskTopicId);
        taskTopicEntity = taskTopicEntity.updateStatus(taskTopicUpdateStatusObj);
        taskTopicDao.save(taskTopicEntity);
    }
}
