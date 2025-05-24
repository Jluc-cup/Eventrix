package com.eventrix.dao.repository;

import com.eventrix.model.entity.TaskTopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaskTopicRepository extends JpaRepository<TaskTopicEntity, Integer> {

    @Query("SELECT n FROM TaskTopicEntity n WHERE n.id =:taskTopicId ")
    TaskTopicEntity getById(int taskTopicId);
}
