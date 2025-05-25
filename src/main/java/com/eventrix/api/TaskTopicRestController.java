package com.eventrix.api;

import com.eventrix.api.dto.generic.IntegerDtoV1;
import com.eventrix.api.req.TaskTopicCreateReqV1;
import com.eventrix.api.req.TaskTopicUpdateActiveReqV1;
import com.eventrix.api.req.TaskTopicUpdateReqV1;
import com.eventrix.base.swagger.annotation.TaskTopicSwaggerDoc;
import com.eventrix.service.TaskTopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class TaskTopicRestController {

    private final TaskTopicService taskTopicService;

    @TaskTopicSwaggerDoc(
            summary = "Create a new task topic",
            description = "Creates a new task topic and returns its ID"
    )
    @PostMapping(value = "/eventrix/api/v1/topic")
    public IntegerDtoV1 createTaskTopic(@RequestBody TaskTopicCreateReqV1 req) {
        int taskTopicId = taskTopicService.create(req);
        return new IntegerDtoV1(taskTopicId);
    }

    @TaskTopicSwaggerDoc(
            summary = "Update a task topic",
            description = "Updates an existing task topic by its ID",
            errorCodes = {"400", "404", "500"}
    )
    @PutMapping("/eventrix/api/v1/topic/{topicId}")
    public void updateTaskTopic(@PathVariable int topicId, @RequestBody TaskTopicUpdateReqV1 req) {
        taskTopicService.update(topicId, req);
    }

    @TaskTopicSwaggerDoc(
            summary = "Update task topic active status",
            description = "Updates the active status of a task topic by its ID",
            errorCodes = {"400", "404", "500"}
    )
    @PutMapping("/eventrix/api/v1/topic/{topicId}/active")
    public void updateTaskTopicActive(@PathVariable int topicId, @RequestBody TaskTopicUpdateActiveReqV1 req) {
        taskTopicService.updateActive(topicId, req);
    }

    @TaskTopicSwaggerDoc(
            summary = "Delete a task topic",
            description = "Deletes a task topic by its ID",
            errorCodes = {"404", "500"}
    )
    @DeleteMapping("/eventrix/api/v1/topic/{topicId}")
    public void deleteTaskTopic(@PathVariable int topicId) {
        taskTopicService.delete(topicId);
    }
}