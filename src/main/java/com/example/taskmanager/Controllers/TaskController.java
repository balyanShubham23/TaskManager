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
    ResponseEntity<TaskEntity> GetTaskById(@PathVariable("id") Integer id, @RequestHeader("my_number") Boolean check)
    {
        if(check)
        return ResponseEntity.ok(taskService.getTaskById(id));
        else
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

    @GetMapping("/tasks/TasksByTitle")
    ResponseEntity<Optional<TaskEntity>> getTasksbyTitle(@RequestParam(value = "title") String title)
    {
        return ResponseEntity.ok(taskService.getTasksbyTitle(title));
    }
    @GetMapping("/tasks/TasksByCompleted")
    ResponseEntity<List<TaskEntity>> getTasksbyCompletedStatus(@RequestParam(value = "status") Boolean status)
    {
        return ResponseEntity.ok(taskService.getTasksbyCompletedStatus(status));
    }

    @ExceptionHandler(TasksService.TaskNotFoundException.class)
    ResponseEntity<ErrorResponse> handleErrors(TasksService.TaskNotFoundException ex)
    {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}
