package com.eventrix.dao.impl;

import com.eventrix.dao.TaskTopicDao;
import com.eventrix.dao.repository.TaskTopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TaskTopicDaoImpl implements TaskTopicDao {

    private final TaskTopicRepository repository;
}
