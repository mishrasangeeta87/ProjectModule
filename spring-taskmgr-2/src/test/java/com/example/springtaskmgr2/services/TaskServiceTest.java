package com.example.springtaskmgr2.services;

import com.example.springtaskmgr2.entities.TaskEntity;
import com.example.springtaskmgr2.exceptions.TaskNotFoundException;
import com.example.springtaskmgr2.repositories.TasksRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class TaskServiceTest {
    @Autowired
    TasksRepository tasksRepository;


    @Test
    public void testShowEmptyTasks() {
        TaskService tasksService = new TaskService(tasksRepository);
        List<TaskEntity> tasks = tasksService.getAllTasks();
        assertEquals(tasks.size(), 0);
    }

    @Test
    public void findTaskIdDoesNotExist() {
        TaskService tasksService = new TaskService(tasksRepository);
        assertThrows(TaskNotFoundException.class, () -> tasksService.getTaskById(100));
    }

    @Test
    public void findTaskTitleDoesNotExist() {
        TaskService tasksService = new TaskService(tasksRepository);
        List<TaskEntity> taskEntities = tasksService.getTasksByTitle("Sangeeta");
        assertEquals(0, taskEntities.size());
    }
}
