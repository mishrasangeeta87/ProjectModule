package com.example.springtaskmgr2.repositories;

import com.example.springtaskmgr2.entities.NoteEntity;
import com.example.springtaskmgr2.entities.TaskEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class NoteRepositoryTest {
    @Autowired
    NotesRepository notesRepository;
    @Autowired
    TasksRepository tasksRepository;

    @Test
    void createNoteForTask() {
        NoteEntity noteEntity = new NoteEntity();
        TaskEntity task = new TaskEntity();
        task.setId(1);
        task.setDescription("Hello Desc");
        task.setTitle("Hello Title");
        task.setDueDate(new Date());
        noteEntity.setTask(task);
        noteEntity.setBody("This is note for the task");
        NoteEntity entity = notesRepository.save(noteEntity);
        assertNotNull(entity);
    }

    @Test
    void getAllNotesForATask() {

        //create task
        TaskEntity task = new TaskEntity();
        task.setId(1);
        task.setDescription("Hello Desc");
        task.setTitle("Hello Title");
        task.setDueDate(new Date());
        tasksRepository.save(task);

        NoteEntity noteEntity1 = new NoteEntity();
        noteEntity1.setTask(task);
        noteEntity1.setBody("This is note 1 for the task");


        NoteEntity noteEntity2 = new NoteEntity();
        noteEntity2.setTask(task);
        noteEntity2.setBody("This is note 2 for the task");

        notesRepository.save(noteEntity1);
        notesRepository.save(noteEntity2);


        List<NoteEntity> noteEntityList = notesRepository.getNoteEntitiesByTaskId(task.getId());
        assertEquals(noteEntityList.size(), 2);
    }

    @Test
    void deleteNoteForATask() {

        //create task
        TaskEntity task = new TaskEntity();
        task.setId(1);
        task.setDescription("Hello Desc");
        task.setTitle("Hello Title");
        task.setDueDate(new Date());
        tasksRepository.save(task);

        NoteEntity noteEntity1 = new NoteEntity();
        noteEntity1.setTask(task);
        noteEntity1.setBody("This is note 1 for the task");


        NoteEntity noteEntity2 = new NoteEntity();
        noteEntity2.setTask(task);
        noteEntity2.setBody("This is note 2 for the task");

        NoteEntity saved1 = notesRepository.save(noteEntity1);
        NoteEntity saved2 = notesRepository.save(noteEntity2);


        int deleted = notesRepository.deleteByTaskIdAndId(task.getId(),saved1.getId() );
        assertEquals(deleted, noteEntity1.getId());
    }

}
