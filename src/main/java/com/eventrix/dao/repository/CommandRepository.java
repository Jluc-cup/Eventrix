package com.eventrix.dao.repository;

import com.eventrix.model.entity.CommandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandRepository extends JpaRepository<CommandEntity, Integer> {
}
