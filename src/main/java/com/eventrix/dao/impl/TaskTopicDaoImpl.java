package com.eventrix.dao.impl;

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
}
