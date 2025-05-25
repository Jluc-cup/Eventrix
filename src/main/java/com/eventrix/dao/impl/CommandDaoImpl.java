package com.eventrix.dao.impl;

import com.eventrix.dao.CommandDao;
import com.eventrix.dao.repository.CommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommandDaoImpl implements CommandDao {

    private final CommandRepository repository;
}
