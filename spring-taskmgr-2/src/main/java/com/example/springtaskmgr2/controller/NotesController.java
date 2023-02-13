package com.example.springtaskmgr2.controller;

import com.example.springtaskmgr2.entities.NoteEntity;
import com.example.springtaskmgr2.services.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping
public class NotesController {

    NoteService noteService;

    public NotesController(NoteService noteService) {
        this.noteService = noteService;
    }


    @GetMapping("tasks/{id}/notes")
    public ResponseEntity<List<NoteEntity>> getTaskNotes(@PathVariable Integer id) {
        return ResponseEntity.ok(noteService.getNotesByTaskId(id));
    }

    @PostMapping("tasks/{id}/notes")
    public ResponseEntity<NoteEntity> createTaskNotes(@PathVariable Integer id,@RequestBody NoteEntity note) {
        NoteEntity createdNote = noteService.createTaskNote(id,note.getBody());
        int noteId=createdNote.getId();
        return ResponseEntity.created(URI.create("tasks/"+id+"/notes/"+noteId)).body(createdNote);

    }

    @DeleteMapping("tasks/{id}/notes/{noteId}")
    public ResponseEntity<NoteEntity> deleteNoteById(@PathVariable Integer id,@PathVariable Integer noteId){
        return ResponseEntity.accepted().body(noteService.deleteNote(id,noteId));
    }
}
