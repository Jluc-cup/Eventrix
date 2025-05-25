package com.eventrix.dao.impl;

import com.eventrix.base.util.ObjectUtil;
import com.eventrix.dao.CommandDao;
import com.eventrix.dao.repository.CommandRepository;
import com.eventrix.model.entity.CommandEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommandDaoImpl implements CommandDao {

    private final CommandRepository repository;

    @Override
    public void save(CommandEntity entity) {
        repository.saveAndFlush(entity);
    }

    @Override
    public CommandEntity findActiveById(int id) {
        final CommandEntity entity = getActiveById(id);
        ObjectUtil.checkNotNull(entity);
        return entity;
    }

    @Override
    public CommandEntity getActiveById(int id) {
        return repository.getActiveById(id);
    }
}
