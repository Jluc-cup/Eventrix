package com.eventrix.dao.impl;

import com.eventrix.base.util.ObjectUtil;
import com.eventrix.dao.TaskTopicDao;
import com.eventrix.dao.repository.TaskTopicRepository;
import com.eventrix.model.entity.TaskTopicEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TaskTopicDaoImpl implements TaskTopicDao {

    private final TaskTopicRepository repository;

    @Override
    public void save(TaskTopicEntity entity) {
        repository.saveAndFlush(entity);
    }

    @Override
    public TaskTopicEntity getById(int taskTopicId) {
        return repository.getById(taskTopicId);
    }

    @Override
    public TaskTopicEntity findById(int taskTopicId) {
        final TaskTopicEntity taskTopicEntity = getById(taskTopicId);
        ObjectUtil.checkNotNull(taskTopicEntity);
        return taskTopicEntity;
    }
}
