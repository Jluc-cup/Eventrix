package com.eventrix.dao;

import com.eventrix.model.entity.CommandEntity;

public interface CommandDao {
    void save(CommandEntity entity);

    CommandEntity findActiveById(int id);

    CommandEntity getActiveById(int id);
}
