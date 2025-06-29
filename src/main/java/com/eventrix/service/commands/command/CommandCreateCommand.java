package com.eventrix.service.commands.command;

import com.eventrix.base.feature.command.Command;
import com.eventrix.base.feature.command.CommandDefinition;
import com.eventrix.base.feature.transaction.TransactionWrapper;
import com.eventrix.dao.CommandDao;
import com.eventrix.model.entity.CommandEntity;
import com.eventrix.model.localobj.CommandCreateObj;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@CommandDefinition(
        bean = CommandCreateCommand.class,
        contextType = CommandCreateObj.class,
        returnType = Integer.class)
public class CommandCreateCommand implements Command<CommandCreateObj, Integer> {

    private final CommandDao commandDao;

    @Override
    public Integer execute(CommandCreateObj obj) {
        final CommandEntity commandEntity = new CommandEntity(obj);
        commandDao.save(commandEntity);
        return commandEntity.getId();
    }
}
