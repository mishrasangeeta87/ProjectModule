package com.example.springtaskmgr2.repositories;

import com.example.springtaskmgr2.entities.TaskEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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
        List<TaskEntity> taskList = tasksRepository.findAll();
        assertNotNull(taskList);
        assertEquals(2, taskList.size());
    }

    @Test
    public void readTasksByIdWorks() {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(500);
        taskEntity.setTitle("Task 5");
        taskEntity.setDescription("Test Desc 5");
        taskEntity.setCompleted(false);
        TaskEntity task = tasksRepository.save(taskEntity);

        Optional<TaskEntity> findTask = tasksRepository.findById(task.getId());
        assertTrue(findTask.isPresent());
        assertEquals(findTask.get().getTitle(), "Task 5");
    }

    @Test
    public void deleteTaskByIdWorks() {
        TaskEntity taskEntity1 = new TaskEntity();
        taskEntity1.setTitle("Task 1");
        taskEntity1.setDescription("Test Desc");
        taskEntity1.setCompleted(false);
        tasksRepository.save(taskEntity1);
        tasksRepository.deleteTaskEntityById(1);
        TaskEntity deleted = tasksRepository.findById(1).orElse(null);
        assertNull(deleted);
    }

    @Test
    public void testUpdateTask() {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTitle("Task 1");
        taskEntity.setDescription("Test Desc");
        taskEntity.setCompleted(false);
        taskEntity.setDueDate(new Date());
        TaskEntity saved = tasksRepository.save(taskEntity);


        //update
        TaskEntity updatedEntity = new TaskEntity();
        updatedEntity.setId(saved.getId());
        updatedEntity.setTitle("Task Updated 1");
        TaskEntity updateResult = tasksRepository.save(updatedEntity);

        //Fetch updated
        assertEquals(updateResult.getTitle(), "Task Updated 1");
        assertEquals(updateResult.getDescription(), saved.getDescription());
    }

    @Test
    public void readTasksByTitleWorks() {
        TaskEntity taskEntity1 = new TaskEntity();
        taskEntity1.setTitle("Task San");
        taskEntity1.setDescription("Test San desc");
        taskEntity1.setCompleted(false);
        tasksRepository.save(taskEntity1);

        Optional<List<TaskEntity>> task = tasksRepository.findAllByTitleEqualsIgnoreCase("task san");
        assertTrue(task.isPresent());
        assertEquals(task.get().get(0).getDescription(), "Test San desc");
    }

    @Test
    public void readTasksByCompletedWorks() {
        TaskEntity taskEntity1 = new TaskEntity();
        taskEntity1.setTitle("Task San 1");
        taskEntity1.setDescription("Test San desc 1");
        taskEntity1.setCompleted(false);

        TaskEntity taskEntity2 = new TaskEntity();
        taskEntity2.setTitle("Task San 2");
        taskEntity2.setDescription("Test San desc 2");
        taskEntity2.setCompleted(true);
        tasksRepository.save(taskEntity1);
        tasksRepository.save(taskEntity2);

        Optional<List<TaskEntity>> taskCompleted = tasksRepository.findAllByCompleted(true);
        assertTrue(taskCompleted.isPresent());
        assertEquals(taskCompleted.get().get(0).getDescription(), "Test San desc 2");

        Optional<List<TaskEntity>> taskNotCompleted = tasksRepository.findAllByCompleted(false);
        assertTrue(taskNotCompleted.isPresent());
        assertEquals(taskNotCompleted.get().get(0).getDescription(), "Test San desc 1");
    }
}
