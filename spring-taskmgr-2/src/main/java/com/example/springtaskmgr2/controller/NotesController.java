package com.example.springtaskmgr2.controller;

import com.example.springtaskmgr2.entities.NoteEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class NotesController {

    @GetMapping("tasks/{id}/notes")
    public void getTaskNotes(@PathVariable Integer id){

    }

    @PostMapping("tasks/{id}/notes")
    public void createTaskNotes(@RequestBody NoteEntity note){

    }

    @DeleteMapping("tasks/{id}/notes/{noteId}")
    public void deleteNoteById(@PathVariable Integer id,@PathVariable Integer noteId){

    }
}
