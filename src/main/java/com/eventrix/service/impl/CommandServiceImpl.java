package com.eventrix.service.impl;

import com.eventrix.api.req.CommandCreateReqV1;
import com.eventrix.api.req.CommandUpdateReqV1;
import com.eventrix.base.feature.command.CommandManager;
import com.eventrix.base.feature.transaction.TransactionWrapper;
import com.eventrix.model.localobj.CommandCreateObj;
import com.eventrix.model.localobj.CommandDeleteObj;
import com.eventrix.model.localobj.CommandUpdateObj;
import com.eventrix.service.CommandService;
import com.eventrix.service.commands.command.CommandCreateCommand;
import com.eventrix.service.commands.command.CommandDeleteCommand;
import com.eventrix.service.commands.command.CommandUpdateCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommandServiceImpl implements CommandService {


    private final CommandManager commandManager;
    private final TransactionWrapper transaction;

    @Override
    public int create(CommandCreateReqV1 req) {
        final CommandCreateObj commandCreateObj = new CommandCreateObj(req);
        return transaction.execute(() -> createT(commandCreateObj));
    }

    private int createT(CommandCreateObj commandCreateObj) {
        return commandManager.executeCommand(CommandCreateCommand.class, commandCreateObj);
    }

    @Override
    public void update(int commandId, CommandUpdateReqV1 req) {
        final CommandUpdateObj commandUpdateObj = new CommandUpdateObj(commandId, req);
        transaction.executeWithoutResult(() -> updateT(commandUpdateObj));
    }

    private void updateT(CommandUpdateObj commandUpdateObj) {
        commandManager.executeCommand(CommandUpdateCommand.class, commandUpdateObj);
    }

    @Override
    public void delete(int commandId) {
        final CommandDeleteObj commandDeleteObj = new CommandDeleteObj(commandId);
        transaction.executeWithoutResult(() -> deleteT(commandDeleteObj));
    }

    private void deleteT(CommandDeleteObj commandDeleteObj) {
        commandManager.executeCommand(CommandDeleteCommand.class, commandDeleteObj);
    }
}
