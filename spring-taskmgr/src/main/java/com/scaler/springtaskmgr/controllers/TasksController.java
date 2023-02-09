package com.scaler.springtaskmgr.controllers;

import com.scaler.springtaskmgr.entities.Task;
import com.scaler.springtaskmgr.exceptions.ErrorResponse;
import com.scaler.springtaskmgr.exceptions.TaskNotFoundException;
import com.scaler.springtaskmgr.services.TasksService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class TasksController {

    private TasksService tasksService;

    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    /*Get all existing tasks*/
    @GetMapping("/tasks")
    ResponseEntity<List<Task>> getTasks() {
        return ResponseEntity.ok(tasksService.getTasks());
    }

    /* Create a new Task*/

    /**
     * @param task
     * @return
     */
    @PostMapping("/tasks")
    ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task newTask = tasksService.createTask(task.getTitle(),
                task.getDescription(),
                task.getDueDate());

        return ResponseEntity.created(URI.create("/tasks/" + newTask.getId())).body(newTask);
    }

    /**
     * @param id
     * @return
     */
    @GetMapping("/tasks/{id}")
    ResponseEntity<Task> getTask(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(tasksService.getTaskById(id));
    }

    /**
     * @param id
     * @return
     */
    @DeleteMapping("/tasks/{id}")
    ResponseEntity<Task> deleteTask(@PathVariable("id") Integer id) {
        return ResponseEntity.accepted().body(tasksService.deleteTask(id));
    }

    /**
     *
     * @param id
     * @param task
     * @return
     */
    @PatchMapping("/tasks/{id}")
    ResponseEntity<Task> updateTask(@PathVariable("id") Integer id, @RequestBody Task task) {
        Task updatedTask = tasksService.updateTask(id, task.getTitle(),
                task.getDescription(),
                task.getDueDate());
        return ResponseEntity.accepted().body(updatedTask);
    }

    @ExceptionHandler({
            TaskNotFoundException.class})
    ResponseEntity<ErrorResponse> handleErrors(TaskNotFoundException e){
        return  new ResponseEntity<>(
                new ErrorResponse(e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }



}
