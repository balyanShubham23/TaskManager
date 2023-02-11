package com.example.taskmanager.repositories;

import com.example.taskmanager.Entities.NoteEntity;
import com.example.taskmanager.Entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotesRepository extends JpaRepository<NoteEntity, Integer> {

    public List<NoteEntity> findAllByTask_Id(Integer id);
}
