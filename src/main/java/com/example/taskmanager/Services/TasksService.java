package com.example.taskmanager.Services;

import com.example.taskmanager.Entities.TaskEntity;
import com.example.taskmanager.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TasksService {

    public static class TaskNotFoundException extends IllegalArgumentException
    {
        public TaskNotFoundException(Integer id)
        {
            super("Tasks for the given id: " + id + " is not found.");
        }
    }
    final TaskRepository taskRepository;

    public TasksService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskEntity> getAllTasks()
    {
        return taskRepository.findAll();
    }

    public TaskEntity getTaskById(Integer id)
    {
        var task = taskRepository.findById(id).get();
        if(task == null)
        {
            throw new TaskNotFoundException(id);
        }
        return task;
    }

    public void CreateTask(TaskEntity taskEntity)
    {
        taskRepository.save(taskEntity);
    }

    public Optional<TaskEntity> getTasksbyTitle(String title)
    {
        return taskRepository.findByTitle(title);
    }

    public List<TaskEntity> getTasksbyCompletedStatus(Boolean status)
    {
        return taskRepository.findAllByCompleted(status);
    }
}
