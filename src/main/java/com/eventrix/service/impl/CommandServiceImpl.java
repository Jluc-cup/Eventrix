package com.eventrix.service.impl;

import com.eventrix.api.req.CommandCreateReqV1;
import com.eventrix.api.req.CommandUpdateReqV1;
import com.eventrix.base.feature.AbstractService;
import com.eventrix.base.feature.transaction.TransactionWrapper;
import com.eventrix.dao.CommandDao;
import com.eventrix.model.localobj.CommandCreateObj;
import com.eventrix.model.localobj.CommandDeleteObj;
import com.eventrix.model.localobj.CommandUpdateObj;
import com.eventrix.service.CommandService;
import com.eventrix.service.strategy.command.CommandCreateStrategy;
import com.eventrix.service.strategy.command.CommandDeleteStrategy;
import com.eventrix.service.strategy.command.CommandUpdateStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommandServiceImpl extends AbstractService implements CommandService {

    public CommandServiceImpl(CommandDao commandDao, TransactionWrapper transaction) {
        registerStrategy(CommandCreateObj.class,
                new CommandCreateStrategy(commandDao, transaction));
        registerStrategy(CommandUpdateObj.class,
                new CommandUpdateStrategy(commandDao, transaction));
        registerStrategy(CommandDeleteObj.class,
                new CommandDeleteStrategy(commandDao, transaction));
    }


    @Override
    public int create(CommandCreateReqV1 req) {
        final CommandCreateObj commandCreateObj = new CommandCreateObj(req);
        return executeOperation(commandCreateObj);
    }

    @Override
    public void update(int commandId, CommandUpdateReqV1 req) {
        final CommandUpdateObj commandUpdateObj = new CommandUpdateObj(commandId, req);
        executeOperation(commandUpdateObj);
    }

    @Override
    public void delete(int commandId) {
        final CommandDeleteObj commandDeleteObj = new CommandDeleteObj(commandId);
        executeOperation(commandDeleteObj);
    }
}
