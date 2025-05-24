package com.eventrix.service.impl;

import com.eventrix.api.req.TaskTopicCreateReqV1;
import com.eventrix.dao.TaskTopicDao;
import com.eventrix.model.entity.TaskTopicEntity;
import com.eventrix.model.localobj.TaskTopicCreateObj;
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
}
