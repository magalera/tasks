package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DbService dbService;
    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void testGetTasks() throws Exception {
        // Given
        List<Task> tasks = new ArrayList<>();
        Task task1 = new Task(1L, "Test Task 1", "Lorem ipsum 1");
        Task task2 = new Task(2L, "Test Task 2", "Lorem ipsum 2");
        Task task3 = new Task(3L, "Test Task 3", "Lorem ipsum 3");
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        List<TaskDto> tasksDto = new ArrayList<>();
        TaskDto taskDto1 = new TaskDto(1L, "Test Task 1", "Lorem ipsum 1");
        TaskDto taskDto2 = new TaskDto(2L, "Test Task 2", "Lorem ipsum 2");
        TaskDto taskDto3 = new TaskDto(3L, "Test Task 3", "Lorem ipsum 3");
        tasksDto.add(taskDto1);
        tasksDto.add(taskDto2);
        tasksDto.add(taskDto3);

        when(dbService.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(tasksDto);

        // When & Then
        mockMvc.perform(get("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(tasks.size())))
                .andExpect(jsonPath("$.[0].id", is(tasks.get(0).getId().intValue())))
                .andExpect(jsonPath("$.[0].title", is(tasks.get(0).getTitle())))
                .andExpect(jsonPath("$.[0].content", is(tasks.get(0).getContent())))
                .andExpect(jsonPath("$.[1].id", is(tasks.get(1).getId().intValue())))
                .andExpect(jsonPath("$.[1].title", is(tasks.get(1).getTitle())))
                .andExpect(jsonPath("$.[1].content", is(tasks.get(1).getContent())))
                .andExpect(jsonPath("$.[2].id", is(tasks.get(2).getId().intValue())))
                .andExpect(jsonPath("$.[2].title", is(tasks.get(2).getTitle())))
                .andExpect(jsonPath("$.[2].content", is(tasks.get(2).getContent())));
    }

    @Test
    public void testGetTask() throws Exception {
        // Given
        Task task = new Task(1L, "Test Task", "Lorem ipsum");
        TaskDto taskDto = new TaskDto(1L, "Test Task", "Lorem ipsum");

        when(dbService.findById(task.getId())).thenReturn(Optional.of(task));
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        // When & Then
        mockMvc.perform(get("/v1/tasks/" + task.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(task.getId().intValue())))
                .andExpect(jsonPath("$.title", is(task.getTitle())))
                .andExpect(jsonPath("$.content", is(task.getContent())));
    }

    @Test
    public void testDeleteTask() throws Exception {
        // Given
        Task task = new Task(1L, "Test Task", "Lorem ipsum");

        when(dbService.findById(task.getId())).thenReturn(Optional.of(task));

        // When & Then
        mockMvc.perform(delete("/v1/tasks/" + task.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateTask() throws Exception {
        // Given
        Task task = new Task(1L, "Task", "Lorem ipsum");
        TaskDto taskDto = new TaskDto(1L, "Task", "Lorem ipsum");

        when(dbService.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        // When & Then
        mockMvc.perform(put("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateTask() throws Exception {
        // Given
        Task task = new Task(null, "Test Task", "Lorem ipsum");
        TaskDto taskDto = new TaskDto(null, "Test Task", "Lorem ipsum");

        when(dbService.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        // When & Then
        mockMvc.perform(post("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }
}