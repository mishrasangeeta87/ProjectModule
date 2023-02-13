package com.example.springtaskmgr2.exceptions;

public class TaskNotFoundException extends IllegalArgumentException {
    public TaskNotFoundException(Integer id) {
        super("Task with id " + id + " not found");
    }
}

