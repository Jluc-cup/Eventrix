package com.eventrix.service.impl;

import com.eventrix.api.req.CommandCreateReqV1;
import com.eventrix.api.req.CommandUpdateReqV1;
import com.eventrix.service.CommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommandServiceImpl implements CommandService {

    @Override
    public int create(CommandCreateReqV1 req) {
        return 0;
    }

    @Override
    public void update(int commandId, CommandUpdateReqV1 req) {

    }

    @Override
    public void delete(int commandId) {

    }
}
