package com.eventrix.dao.impl;

import com.eventrix.dao.TaskDao;
import com.eventrix.dao.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TaskDaoImpl implements TaskDao {

    public final TaskRepository repository;
}
