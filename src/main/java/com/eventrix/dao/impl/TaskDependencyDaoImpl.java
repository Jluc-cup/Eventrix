package com.eventrix.dao.impl;

import com.eventrix.dao.TaskDependencyDao;
import com.eventrix.dao.repository.TaskDependencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TaskDependencyDaoImpl implements TaskDependencyDao {

    private final TaskDependencyRepository repository;
}
