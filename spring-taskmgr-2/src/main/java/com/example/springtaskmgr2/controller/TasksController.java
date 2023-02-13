package com.example.springtaskmgr2.controller;

import com.example.springtaskmgr2.entities.TaskEntity;
import com.example.springtaskmgr2.services.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    @Autowired
    private TasksService tasksService;

    @GetMapping
    public ResponseEntity<List<TaskEntity>> getTasks() {
        List<TaskEntity> tasks = tasksService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskEntity> getTasksById(@PathVariable Integer id) {
        TaskEntity taskEntity = tasksService.getTaskById(id);
        return ResponseEntity.ok(taskEntity);
    }

    @PostMapping
    public ResponseEntity<TaskEntity> createTask(@RequestBody TaskEntity task) {
        TaskEntity newTask = tasksService.createTask(task.getTitle(), task.getDescription(), task.getDueDate());
        return ResponseEntity.created(URI.create("/tasks/" + newTask.getId())).body(newTask);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable Integer id, @RequestBody TaskEntity task) {
        TaskEntity updatedTask = tasksService.updateTask(id, task.getTitle(),
                task.getDescription(), task.getDueDate(), task.getCompleted());
        return ResponseEntity.accepted().body(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskEntity> deleteTaskById(@PathVariable Integer id) {
        return ResponseEntity.accepted().body(tasksService.deleteTaskById(id));
    }

    @GetMapping("?title={title}")
    public ResponseEntity<List<TaskEntity>> getTasksByTitle(@PathVariable(name = "title") String title) {
        List<TaskEntity> tasks = tasksService.getTasksByTitle(title);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("?completed={completed}")
    public ResponseEntity<List<TaskEntity>> getCompletedTasks(@PathVariable(name = "completed") Boolean completed) {
        List<TaskEntity> tasks = tasksService.getTasksByCompleted(completed);
        return ResponseEntity.ok(tasks);

    }

//    @ExceptionHandler({TaskNotFoundException.class})
//    ResponseEntity<ErrorResponse> handleErrors(TaskNotFoundException e) {
//        return new ResponseEntity<>(new ErrorResponse(e.getMessage()),
//                HttpStatus.NOT_FOUND);
//    }


}
