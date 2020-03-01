package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/task")
public class TaskController {

    @RequestMapping(value = "/getTasks", method = RequestMethod.GET)
    public List<TaskDto> getTasks() {
        return new ArrayList<>();
    }

    @RequestMapping(value = "/getTask/{id}", method = RequestMethod.GET)
    public TaskDto getTask(@PathVariable Long id) {
        return new TaskDto(1L, "test title", "test content");
    }

    @RequestMapping(value = "/deleteTask/{id}", method = RequestMethod.DELETE)
    public void deleteTask(@PathVariable Long id) {

    }

    @RequestMapping(value = "/updateTask", method = RequestMethod.PUT)
    public TaskDto updateTask(TaskDto taskDto) {
        return new TaskDto(1L, "test title", "test content");
    }

    @RequestMapping(value = "/createTask", method = RequestMethod.POST)
    public void createTask(TaskDto taskDto) {

    }

}
