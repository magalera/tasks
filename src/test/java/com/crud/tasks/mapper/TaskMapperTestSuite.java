package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTestSuite {
    @Autowired
    private TaskMapper taskMapper;

    private void compare(TaskDto taskDto, Task task) {
        assertEquals(task.getId(), taskDto.getId());
        assertEquals(task.getTitle(), taskDto.getTitle());
        assertEquals(task.getContent(), taskDto.getContent());
    }

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Task_1", "Lorem ipsum");

        //When
        Task actual = taskMapper.mapToTask(taskDto);

        //Then
        compare(taskDto, actual);
    }

    @Test
    public void testMapToTaskDto() {
        //Given
        Task task = new Task(2L, "Task_2", "Lorem ipsum 2");

        //When
        TaskDto actual = taskMapper.mapToTaskDto(task);

        //Then
        compare(actual, task);
    }

    @Test
    public void testMapToTaskDtoList() {
        //Given
        List<Task> tasks = new ArrayList<>();
        Task task1 = new Task(1L, "Task_1", "Lorem ipsum 1");
        Task task2 = new Task(2L, "Task_2", "Lorem ipsum 2");
        Task task3 = new Task(3L, "Task_3", "Lorem ipsum 3");
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        //When
        List<TaskDto> actual = taskMapper.mapToTaskDtoList(tasks);

        //Then
        assertEquals(actual.size(), tasks.size());
        for (int i = 0; i < tasks.size(); i++) {
            compare(actual.get(i), tasks.get(i));
        }
    }
}
