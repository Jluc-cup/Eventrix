package com.eventrix.service.strategy.command;

import com.eventrix.base.feature.transaction.TransactionWrapper;
import com.eventrix.dao.CommandDao;
import com.eventrix.model.entity.CommandEntity;
import com.eventrix.model.localobj.CommandCreateObj;
import com.eventrix.service.strategy.OperationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommandCreateStrategy implements OperationStrategy<CommandCreateObj, Integer> {

    private final CommandDao commandDao;

    private final TransactionWrapper transaction;

    @Override
    public Integer execute(CommandCreateObj obj) {
        final CommandEntity commandEntity = transaction.execute(() -> createT(obj));
        return commandEntity.getId();
    }


    private CommandEntity createT(CommandCreateObj obj) {
        final CommandEntity commandEntity = new CommandEntity(obj);
        commandDao.save(commandEntity);
        return commandEntity;
    }
}
