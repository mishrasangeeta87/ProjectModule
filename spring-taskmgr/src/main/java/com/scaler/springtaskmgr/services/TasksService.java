package com.scaler.springtaskmgr.services;

import com.scaler.springtaskmgr.entities.Task;
import com.scaler.springtaskmgr.exceptions.TaskNotFoundException;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TasksService {
    private final List<Task> taskList;
    private AtomicInteger taskId = new AtomicInteger(0);
    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public TasksService() {
        this.taskList = new ArrayList<>();

        Task task1 = new Task(taskId.incrementAndGet(), "Task 1", "Description 1", "2021-01-23");
        taskList.add(task1);

        Task task2 = new Task(taskId.incrementAndGet(), "Task 2", "Description 2", "2021-01-23");
        taskList.add(task2);

        Task task3 = new Task(taskId.incrementAndGet(), "Task 3", "Description 3", "2021-01-23");
        taskList.add(task3);


    }

   public List<Task> getTasks() {
        return taskList;
    }

    public Task createTask(String title, String description, String dueDate) {
        Task newTask = new Task(taskId.incrementAndGet(), title, description,dueDate);
        taskList.add(newTask);
        return newTask;
    }

    public Task getTaskById(Integer id) {
        return taskList.stream().filter(task -> task.getId().equals(id)).findFirst().orElseThrow(() -> new TaskNotFoundException(id));
    }

    public Task updateTask(Integer id, String title, String description, String dueDate) {
        Task task = getTaskById(id);
        if (title != null)
            task.setTitle(title);
        if (description != null)
            task.setDescription(description);
        if (dueDate != null)
            task.setDueDate(dueDate);

        return task;
    }

    public Task deleteTask(Integer id) {
        Task task = getTaskById(id);
        taskList.remove(task);
        return task;
    }

}
