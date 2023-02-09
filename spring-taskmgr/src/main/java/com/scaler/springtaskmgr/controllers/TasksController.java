package com.scaler.springtaskmgr.controllers;

import com.scaler.springtaskmgr.ResourceNotFoundException;
import com.scaler.springtaskmgr.entities.Task;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class TasksController {
    private final List<Task> taskList;
    private Map<Integer, Task> idTaskMap;
    private AtomicInteger taskId = new AtomicInteger(0);

    public TasksController() {
        idTaskMap = new HashMap<>();
        taskList = new ArrayList<>();
        Task task1 = new Task(taskId.incrementAndGet(), "Task 1", "Description 1", new Date());
        taskList.add(task1);
        idTaskMap.put(taskId.get(), task1);

        Task task2 = new Task(taskId.incrementAndGet(), "Task 2", "Description 2", new Date());
        taskList.add(task2);
        idTaskMap.put(taskId.get(), task2);

        Task task3 = new Task(taskId.incrementAndGet(), "Task 3", "Description 3", new Date());
        taskList.add(task3);
        idTaskMap.put(taskId.get(), task3);


    }

    /*Get all existing tasks*/
    @GetMapping("/tasks")
    List<Task> getTasks() {
        return taskList;
    }

    /* Create a new Task*/
    @PostMapping("/tasks")
    Task createTask(@RequestBody Task task) {
        Task newTask = new Task(taskId.incrementAndGet(), task.getTitle(), task.getDescription(), task.getDueDate());
        taskList.add(newTask);
        idTaskMap.put(taskId.get(), newTask);
        return newTask;
    }

    /**
     * Get a task by id
     */
    @GetMapping("/tasks/{id}")
    Task getTask(@PathVariable("id") Integer id) {
        //return 404 if not found
        Task task = idTaskMap.get(id);
        if (task == null) {
            throw new ResourceNotFoundException();
        }
        return task;
    }

    /**
     * @param id
     * @return
     */
    @DeleteMapping("/tasks/{id}")
    Task deleteTask(@PathVariable("id") Integer id) {
        Task task = idTaskMap.get(id);
        if (task == null) {
            throw new ResourceNotFoundException();
        }
        taskList.remove(task);
        idTaskMap.remove(id);
        return task;
    }

    @PatchMapping("/tasks/{id}")
    Task updateTask(@PathVariable("id") Integer id, @RequestBody Task task) {
        Task foundTask = idTaskMap.get(id);
        taskList.remove(foundTask);
        idTaskMap.remove(id);
        if (foundTask == null) {
            throw new ResourceNotFoundException();
        }

        if (task.getId() != null) {
            foundTask.setId(task.getId());
        }
        if (task.getTitle() != null) {
            foundTask.setTitle(task.getTitle());
        }
        if (task.getDescription() != null) {
            foundTask.setDescription(task.getDescription());
        }
        if (task.getDueDate() != null) {
            foundTask.setDueDate(task.getDueDate());
        }

        taskList.add(foundTask);
        idTaskMap.put(foundTask.getId(), foundTask);
        return foundTask;
    }


}
