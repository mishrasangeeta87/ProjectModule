package com.scaler.springtaskmgr.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class TaskNotFoundException extends IllegalStateException{
    public TaskNotFoundException(Integer id){
        super("Task with id "+ id + " not found");
    }
}
