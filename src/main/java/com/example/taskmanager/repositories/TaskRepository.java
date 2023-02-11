package com.example.taskmanager.repositories;

import com.example.taskmanager.Entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {
    List<TaskEntity> findAllByCompleted(boolean completed);

    Optional<TaskEntity> findByTitle(String title);

    TaskEntity getById(Integer id);
}
