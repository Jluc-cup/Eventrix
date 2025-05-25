package com.eventrix.service.strategy.command;

import com.eventrix.base.feature.transaction.TransactionWrapper;
import com.eventrix.dao.CommandDao;
import com.eventrix.model.entity.CommandEntity;
import com.eventrix.model.localobj.CommandDeleteObj;
import com.eventrix.service.strategy.OperationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommandDeleteStrategy implements OperationStrategy<CommandDeleteObj, Void> {

    private final CommandDao commandDao;

    private final TransactionWrapper transaction;

    @Override
    public Void execute(CommandDeleteObj obj) {
        transaction.executeWithoutResult(() -> deleteT(obj));
        return null;
    }


    private void deleteT(CommandDeleteObj obj) {
        CommandEntity commandEntity = commandDao.findActiveById(obj.id());
        commandEntity = commandEntity.delete();
        commandDao.save(commandEntity);
    }
}
