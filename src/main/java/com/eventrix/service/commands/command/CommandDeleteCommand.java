package com.eventrix.service.commands.command;

import com.eventrix.base.feature.command.Command;
import com.eventrix.base.feature.command.CommandDefinition;
import com.eventrix.base.feature.transaction.TransactionWrapper;
import com.eventrix.dao.CommandDao;
import com.eventrix.model.entity.CommandEntity;
import com.eventrix.model.localobj.CommandDeleteObj;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@CommandDefinition(bean = CommandDeleteCommand.class,
        contextType = CommandDeleteObj.class,
        returnType = Void.class)
public class CommandDeleteCommand implements Command<CommandDeleteObj, Void> {

    private final CommandDao commandDao;

    @Override
    public Void execute(CommandDeleteObj obj) {
        CommandEntity commandEntity = commandDao.findActiveById(obj.id());
        commandEntity = commandEntity.delete();
        commandDao.save(commandEntity);
        return null;
    }

}
