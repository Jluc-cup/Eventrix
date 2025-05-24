package com.eventrix.dao;

import com.eventrix.model.entity.TaskTopicEntity;

public interface TaskTopicDao {

    void save(TaskTopicEntity entity);

    TaskTopicEntity getById(int taskTopicId);

    TaskTopicEntity findById(int taskTopicId);
}
