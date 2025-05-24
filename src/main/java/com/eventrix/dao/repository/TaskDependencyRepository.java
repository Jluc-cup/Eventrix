package com.eventrix.dao.repository;

import com.eventrix.model.entity.TaskDependencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskDependencyRepository extends JpaRepository<TaskDependencyEntity, Long> {
}
