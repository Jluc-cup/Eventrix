package com.eventrix.api;

import com.eventrix.api.dto.generic.IntegerDtoV1;
import com.eventrix.api.req.CommandCreateReqV1;
import com.eventrix.api.req.CommandUpdateReqV1;
import com.eventrix.base.swagger.annotation.CommandSwaggerDoc;
import com.eventrix.service.CommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class CommandRestController {

    private final CommandService commandService;


    @CommandSwaggerDoc(
            summary = "Create a new command",
            description = "Creates a new command and returns its ID"
    )
    @PostMapping(value = "/eventrix/api/v1/command")
    public IntegerDtoV1 create(@RequestBody CommandCreateReqV1 req) {
        int commandId = commandService.create(req);
        return new IntegerDtoV1(commandId);
    }


    @CommandSwaggerDoc(
            summary = "Update a command",
            description = "Updates an existing command by its ID",
            errorCodes = {"400", "404", "500"}
    )
    @PutMapping(value = "/eventrix/api/v1/command/{commandId}")
    public void update(@PathVariable int commandId, @RequestBody CommandUpdateReqV1 req) {
        commandService.update(commandId, req);
    }


    @CommandSwaggerDoc(
            summary = "Delete a command",
            description = "Deletes a command by its ID",
            errorCodes = {"404", "500"}
    )
    @DeleteMapping(value = "/eventrix/api/v1/command/{commandId}")
    public void delete(@PathVariable int commandId) {
        commandService.delete(commandId);
    }


}
