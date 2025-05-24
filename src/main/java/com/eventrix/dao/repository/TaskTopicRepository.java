package com.eventrix.dao.repository;

import com.eventrix.model.entity.TaskTopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskTopicRepository extends JpaRepository<TaskTopicEntity, Integer> {
}
