package com.eventrix.service.strategy.command;

import com.eventrix.base.feature.transaction.TransactionWrapper;
import com.eventrix.dao.CommandDao;
import com.eventrix.model.entity.CommandEntity;
import com.eventrix.model.localobj.CommandUpdateObj;
import com.eventrix.service.strategy.OperationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommandUpdateStrategy implements OperationStrategy<CommandUpdateObj, Void> {

    private final CommandDao commandDao;

    private final TransactionWrapper transaction;

    @Override
    public Void execute(CommandUpdateObj obj) {
        transaction.executeWithoutResult(() -> updateT(obj));
        return null;
    }


    private void updateT(CommandUpdateObj obj) {
        CommandEntity commandEntity = commandDao.findActiveById(obj.id());
        commandEntity = commandEntity.update(obj);
        commandDao.save(commandEntity);
    }
}
