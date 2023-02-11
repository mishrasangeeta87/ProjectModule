package com.example.springtaskmgr2.repositories;

import com.example.springtaskmgr2.entities.TaskEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class TasksRepositoryTests {

    @Autowired
    TasksRepository tasksRepository;

    @Test
    public void testCreateTask() {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTitle("Task 1");
        taskEntity.setDescription("Test Desc");
        taskEntity.setCompleted(false);
        taskEntity.setDueDate(new Date());
        TaskEntity entity = tasksRepository.save(taskEntity);
        assertNotNull(entity);
    }

    @Test
    public void readTasksWorks(){
        TaskEntity taskEntity1 = new TaskEntity();
        taskEntity1.setTitle("Task 1");
        taskEntity1.setDescription("Test Desc");
        taskEntity1.setCompleted(false);
        TaskEntity taskEntity2 = new TaskEntity();
        taskEntity2.setTitle("Task 2");
        taskEntity2.setDescription("Test Desc");
        taskEntity2.setCompleted(false);

  tasksRepository.save(taskEntity1);
        tasksRepository.save(taskEntity2);
        List<TaskEntity> taskList= tasksRepository.findAll();
        assertNotNull(taskList);
        assertEquals(2,taskList.size());
    }
}
