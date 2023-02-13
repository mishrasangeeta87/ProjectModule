package com.example.springtaskmgr2.services;

import com.example.springtaskmgr2.entities.NoteEntity;
import com.example.springtaskmgr2.exceptions.TaskNotFoundException;
import com.example.springtaskmgr2.repositories.NotesRepository;
import com.example.springtaskmgr2.repositories.TasksRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class NoteServiceTest {

    @Autowired
    NotesRepository notesRepository;
    @Autowired
    TasksRepository tasksRepository;

    NoteService noteService;

    @Test
    void createNoteForNonExistentTask(){
        noteService = new NoteService(notesRepository,tasksRepository);
        assertThrows(TaskNotFoundException.class,()->noteService.createTaskNote(1,"Task 1 note "));
    }

    @Test
    void deleteNoteForNonExistentTask(){
        noteService = new NoteService(notesRepository,tasksRepository);
        assertThrows(TaskNotFoundException.class,()->noteService.deleteNote(1,1));
    }

    @Test
    void getAllNotesForATaskId(){
        noteService = new NoteService(notesRepository,tasksRepository);
        List<NoteEntity> entities =noteService.getNotesByTaskId(1);
        assertEquals(entities.size(),0);
    }


}
