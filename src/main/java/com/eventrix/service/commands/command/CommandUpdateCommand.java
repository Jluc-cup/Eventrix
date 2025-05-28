package com.eventrix.service.commands.command;

import com.eventrix.base.feature.command.Command;
import com.eventrix.base.feature.command.CommandDefinition;
import com.eventrix.base.feature.command.CommandNames;
import com.eventrix.base.feature.transaction.TransactionWrapper;
import com.eventrix.dao.CommandDao;
import com.eventrix.model.entity.CommandEntity;
import com.eventrix.model.localobj.CommandDeleteObj;
import com.eventrix.model.localobj.CommandUpdateObj;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@CommandDefinition(name = CommandNames.COMMAND_UPDATE,
        contextType = CommandUpdateObj.class,
        returnType = Void.class)
public class CommandUpdateCommand implements Command<CommandUpdateObj, Void> {

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
