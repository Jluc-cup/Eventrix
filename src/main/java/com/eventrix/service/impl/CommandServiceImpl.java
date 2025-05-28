package com.eventrix.service.impl;

import com.eventrix.api.req.CommandCreateReqV1;
import com.eventrix.api.req.CommandUpdateReqV1;
import com.eventrix.base.feature.command.CommandManager;
import com.eventrix.base.feature.command.CommandNames;
import com.eventrix.model.localobj.CommandCreateObj;
import com.eventrix.model.localobj.CommandDeleteObj;
import com.eventrix.model.localobj.CommandUpdateObj;
import com.eventrix.service.CommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommandServiceImpl implements CommandService {


    private final CommandManager commandManager;


    @Override
    public int create(CommandCreateReqV1 req) {
        final CommandCreateObj commandCreateObj = new CommandCreateObj(req);
        return commandManager.executeCommand(CommandNames.COMMAND_CREATE, commandCreateObj);
    }

    @Override
    public void update(int commandId, CommandUpdateReqV1 req) {
        final CommandUpdateObj commandUpdateObj = new CommandUpdateObj(commandId, req);
        commandManager.executeCommand(CommandNames.COMMAND_UPDATE, commandUpdateObj);
    }

    @Override
    public void delete(int commandId) {
        final CommandDeleteObj commandDeleteObj = new CommandDeleteObj(commandId);
        commandManager.executeCommand(CommandNames.COMMAND_DELETE, commandDeleteObj);
    }
}
