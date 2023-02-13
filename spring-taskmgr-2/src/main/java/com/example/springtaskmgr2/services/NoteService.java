package com.example.springtaskmgr2.services;

import com.example.springtaskmgr2.entities.NoteEntity;
import com.example.springtaskmgr2.entities.TaskEntity;
import com.example.springtaskmgr2.exceptions.TaskNotFoundException;
import com.example.springtaskmgr2.repositories.NotesRepository;
import com.example.springtaskmgr2.repositories.TasksRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    NotesRepository notesRepository;
    TasksRepository tasksRepository;

    public NoteService(NotesRepository notesRepository, TasksRepository tasksRepository) {
        this.notesRepository = notesRepository;
        this.tasksRepository = tasksRepository;
    }

    public List<NoteEntity> getNotesByTaskId(Integer id) {
        return notesRepository.getNoteEntitiesByTaskId(id);
    }

    public NoteEntity createTaskNote(Integer id, String body) {
        Optional<TaskEntity> task = tasksRepository.findById(id);
        if (task.isPresent()) {
            NoteEntity noteEntity = new NoteEntity();
            noteEntity.setTask(task.get());
            noteEntity.setBody(body);
            return notesRepository.save(noteEntity);
        } else {
            throw new TaskNotFoundException(id);
        }
    }

    public NoteEntity deleteNote(Integer taskId, Integer noteId) {
        Optional<TaskEntity> task = tasksRepository.findById(taskId);
        if (task.isPresent()) {
            NoteEntity noteEntity = notesRepository.getNoteEntityByTaskAndId(task.get(), noteId);
            notesRepository.delete(noteEntity);
            return noteEntity;
        } else {
            throw new TaskNotFoundException(taskId);
        }
    }
}
