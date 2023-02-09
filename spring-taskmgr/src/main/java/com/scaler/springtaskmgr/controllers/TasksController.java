package com.scaler.springtaskmgr.controllers;

import com.scaler.springtaskmgr.entities.Task;
import com.scaler.springtaskmgr.services.TasksService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TasksController {

    private TasksService tasksService;

    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    /*Get all existing tasks*/
    @GetMapping("/tasks")
    List<Task> getTasks() {
       return tasksService.getTasks();
    }

    /* Create a new Task*/
    @PostMapping("/tasks")
    Task createTask(@RequestBody Task task) {
        return tasksService.createTask(task.getTitle(), task.getDescription(), task.getDueDate());
    }

    /**
     * Get a task by id
     */
    @GetMapping("/tasks/{id}")
    Task getTask(@PathVariable("id") Integer id) {
        return tasksService.getTaskById(id);
    }

    /**
     * @param id
     * @return
     */
    @DeleteMapping("/tasks/{id}")
    Task deleteTask(@PathVariable("id") Integer id) {
        return tasksService.deleteTask(id);
    }

    /**
     *
     * @param id
     * @param task
     * @return
     */
    @PatchMapping("/tasks/{id}")
    Task updateTask(@PathVariable("id") Integer id, @RequestBody Task task) {
        return tasksService.updateTask(id, task.getTitle(),
                task.getDescription(),
                task.getDueDate());
    }



}
