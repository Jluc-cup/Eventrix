package com.eventrix.service;

import com.eventrix.api.req.CommandCreateReqV1;
import com.eventrix.api.req.CommandUpdateReqV1;

public interface CommandService {

    int create(CommandCreateReqV1 req);

    void update(int commandId, CommandUpdateReqV1 req);

    void delete(int commandId);
}
