package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/task")
public class TaskController {

    @Autowired
    private DbService dbService;
    @Autowired
    private TaskMapper taskMapper;

    @RequestMapping(value = "/getTasks", method = RequestMethod.GET)
    public List<TaskDto> getTasks() {
        return taskMapper.mapToTaskDtoList(dbService.getAllTasks());
    }

    @RequestMapping(value = "/getTask", method = RequestMethod.GET)
    public TaskDto getTask(@RequestParam Long id) throws TaskNotFoundException {
        return taskMapper.mapToTaskDto(dbService.findById(id).orElseThrow(TaskNotFoundException::new));
    }

    @RequestMapping(value = "/deleteTask", method = RequestMethod.DELETE)
    public void deleteTask(@RequestParam Long id) {

    }

    @RequestMapping(value = "/updateTask", method = RequestMethod.PUT)
    public TaskDto updateTask(@RequestBody TaskDto taskDto) {
        return taskMapper.mapToTaskDto(dbService.saveTask(taskMapper.mapToTask(taskDto)));
    }

    @RequestMapping(value = "/createTask", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody TaskDto taskDto) {
        dbService.saveTask(taskMapper.mapToTask(taskDto));
    }

}
