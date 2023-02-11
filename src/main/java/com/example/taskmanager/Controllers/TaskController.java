package com.example.taskmanager.Controllers;

import com.example.taskmanager.Entities.TaskEntity;
import com.example.taskmanager.Services.TasksService;
import com.example.taskmanager.dtos.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class TaskController {
    final TasksService taskService;

    public TaskController(TasksService taskService)
    {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    List<TaskEntity> GetAllTasks()
    {
        return taskService.getAllTasks();
    }

    @GetMapping("/tasks/{id}")
    ResponseEntity<Optional<TaskEntity>> GetTaskById(@PathVariable("id") Integer id)
    {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PostMapping("/tasks")
    ResponseEntity<TaskEntity> createTask(@RequestBody TaskEntity taskEntity)
    {
        var newTask = new TaskEntity(taskEntity.getTitle(), taskEntity.getDescription(),
                taskEntity.getCompleted(),taskEntity.getDueDate());
        taskService.CreateTask(newTask);
        return ResponseEntity.created(URI.create("/GetTaskById/"+newTask.getId())).body(newTask);
    }

    @ExceptionHandler(TasksService.TaskNotFoundException.class)
    ResponseEntity<ErrorResponse> handleErrors(TasksService.TaskNotFoundException ex)
    {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}
