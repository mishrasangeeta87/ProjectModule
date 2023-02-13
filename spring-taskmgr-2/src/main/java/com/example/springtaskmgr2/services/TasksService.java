package com.example.springtaskmgr2.services;

import com.example.springtaskmgr2.entities.TaskEntity;
import com.example.springtaskmgr2.exceptions.TaskNotFoundException;
import com.example.springtaskmgr2.repositories.NotesRepository;
import com.example.springtaskmgr2.repositories.TasksRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TasksService {
    // @Autowired
    TasksRepository tasksRepository;
    //@Autowired
    NotesRepository notesRepository;

    public TasksService(TasksRepository tasksRepository, NotesRepository notesRepository) {
        this.tasksRepository = tasksRepository;
        this.notesRepository = notesRepository;
    }

    public List<TaskEntity> getAllTasks() {
        return tasksRepository.findAll();
    }

    public TaskEntity createTask(String title, String description, Date dueDate) {
        TaskEntity newTask = new TaskEntity();
        newTask.setTitle(title);
        newTask.setCompleted(false);
        newTask.setDescription(description);
        newTask.setDueDate(dueDate);
        newTask.setCompleted(false);
        tasksRepository.save(newTask);
        return newTask;
    }

    public TaskEntity updateTask(Integer id, String title, String description, Date dueDate, Boolean isCompleted) {

        TaskEntity newTask = new TaskEntity();
        if (id != null)
            newTask.setId(id);

        if (title != null)
            newTask.setTitle(title);

        if (isCompleted != null)
            newTask.setCompleted(isCompleted);

        if (description != null)
            newTask.setDescription(description);

        if (dueDate != null)
            newTask.setDueDate(dueDate);

        tasksRepository.save(newTask);
        return newTask;
    }

    public TaskEntity getTaskById(Integer id) {
        Optional<TaskEntity> task = tasksRepository.findById(id);
        return task.orElseThrow(() -> new TaskNotFoundException(id));
    }

    public TaskEntity deleteTaskById(Integer id) {
        Optional<TaskEntity> task = tasksRepository.deleteTaskEntityById(id);
        return task.orElseThrow(() -> new TaskNotFoundException(id));
    }

    public List<TaskEntity> getTasksByTitle(String title) {
        Optional<List<TaskEntity>> tasksWithTitle = tasksRepository.findAllByTitleEqualsIgnoreCase(title);
        return tasksWithTitle.orElseThrow(() -> new TaskNotFoundException(0));
    }

    public List<TaskEntity> getTasksByCompleted(Boolean completed) {
        Optional<List<TaskEntity>> tasksWithCompletedStatus = tasksRepository.findAllByCompleted(completed);
        return tasksWithCompletedStatus.orElseThrow(() -> new TaskNotFoundException(0));
    }


}


