package com.example.taskmanager.Services;

import com.example.taskmanager.Entities.NoteEntity;
import com.example.taskmanager.repositories.NotesRepository;
import com.example.taskmanager.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotesService {

    final NotesRepository notesRepository;

    public NotesService(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    public List<NoteEntity> findbytaskId(Integer  id)
    {
        return notesRepository.findAllByTask_Id (id);
    }

    public NoteEntity addNote(NoteEntity  newNote)
    {
        return notesRepository.save(newNote);
    }

    public void deleteNode(Integer id)
    {
        notesRepository.deleteById(id);
    }
}
