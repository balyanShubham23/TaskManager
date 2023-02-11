package com.example.taskmanager.Controllers;

import com.example.taskmanager.Entities.NoteEntity;
import com.example.taskmanager.Entities.TaskEntity;
import com.example.taskmanager.Services.NotesService;
import com.example.taskmanager.Services.TasksService;
import org.aspectj.weaver.ast.Not;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class NotesController {
    final NotesService notesService;

    final TasksService tasksService;
    public NotesController(NotesService notesService, TasksService tasksService) {
        this.notesService = notesService;
        this.tasksService = tasksService;
    }

    @GetMapping("/tasks/{id}/notes")
    List<NoteEntity> findAllNotes(@PathVariable("id") Integer id)
    {
        return notesService.findbytaskId(id);
    }

    @PostMapping("/tasks/{id}/notes")
    NoteEntity addNote(@RequestBody NoteEntity noteEntity,@PathVariable("id") Integer id)
    {
        var Task = tasksService.getTaskById(id);
        var newNote = new NoteEntity(noteEntity.getBody());
        newNote.setTask(Task);
        return notesService.addNote(newNote);
    }
}
