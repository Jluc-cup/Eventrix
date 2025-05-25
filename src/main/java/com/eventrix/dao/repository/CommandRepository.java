package com.eventrix.dao.repository;

import com.eventrix.model.entity.CommandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommandRepository extends JpaRepository<CommandEntity, Integer> {

    @Query("SELECT n FROM CommandEntity n WHERE n.id =:id AND n.deleted IS NULL")
    CommandEntity getActiveById(int id);
}
