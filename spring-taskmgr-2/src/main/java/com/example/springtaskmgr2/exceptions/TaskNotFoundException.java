package com.example.springtaskmgr2.exceptions;

public class TaskNotFoundException extends IllegalArgumentException {
    public TaskNotFoundException(Integer id) {
        super("Task with id " + id + " not found");
    }

    public TaskNotFoundException(String title) {
        super("Task with title " + title + " not found");
    }

    public TaskNotFoundException(Boolean status) {
        super("Task with completed " + status + " not found");
    }
}

