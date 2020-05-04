package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DbServiceTestSuite {

    @Autowired
    private DbService dbService;
    @Autowired
    private TaskRepository taskRepository;

    private List<Task> createTaskList() {
        List<Task> tasks = new ArrayList<>();
        Task task1 = new Task(null, "Task_1", "Lorem ipsum 1");
        Task task2 = new Task(null, "Task_2", "Lorem ipsum 2");
        Task task3 = new Task(null, "Task_3", "Lorem ipsum 3");
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        return tasks;
    }

    private void saveTaskList(List<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            dbService.saveTask(tasks.get(i));
        }
    }

    @Test
    public void testGetAllTasks() {
        // Given
        List<Task> tasks = createTaskList();

        // When
        saveTaskList(tasks);
        List<Task> actual = dbService.getAllTasks();

        // Then
        assertEquals(3, actual.size());

        // Clean up
        taskRepository.deleteAll(tasks);
    }

    @Test
    public void testFindById() {
        // Given
        Task task = new Task(null, "Task_1", "Lorem ipsum 1");

        // When
        Task savedTask = dbService.saveTask(task);
        Optional<Task> actual = dbService.findById(savedTask.getId());

        // Then
        assertTrue(actual.isPresent());
        assertEquals(task.getId(), actual.get().getId());
        assertEquals(task.getTitle(), actual.get().getTitle());
        assertEquals(task.getId(), actual.get().getId());

        // Clean up
        dbService.deleteById(actual.get().getId());
    }

    @Test
    public void testSaveTask() {
        // Given
        Task task = new Task(null, "Task_1", "Lorem ipsum 1");

        // When
        Task actual = dbService.saveTask(task);

        // Then
        Optional<Task> byId = dbService.findById(actual.getId());
        assertTrue(byId.isPresent());
        assertEquals(task.getContent(), byId.get().getContent());
        assertEquals(task.getTitle(), byId.get().getTitle());
        assertEquals(task.getId(), byId.get().getId());

        // Clean up
        dbService.deleteById(byId.get().getId());
    }

    @Test
    public void testDeleteById() {
        // Given
        Task task = new Task(null, "Task_1", "Lorem ipsum 1");

        // When
        Task savedTask = dbService.saveTask(task);
        dbService.deleteById(savedTask.getId());

        // Then
        Optional<Task> notExist = dbService.findById(savedTask.getId());
        assertFalse(notExist.isPresent());
    }
}