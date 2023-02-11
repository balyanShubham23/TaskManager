package com.example.taskmanager.repositories;

import com.example.taskmanager.Entities.TaskEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class TaskRepositoriesTest {

    @Autowired
    TaskRepository taskRepository;

    @Test
    public void readTasksWorks()
    {
        TaskEntity task1 = new TaskEntity();
        task1.setTitle("Test task");
        task1.setDescription("Test Description");
        task1.setCompleted(false);
        TaskEntity task2 = new TaskEntity();
        task2.setTitle("Test task 2");
        task2.setDescription("Test Description 2");
        task2.setCompleted(false);

        taskRepository.save(task1);
        taskRepository.save(task2);

        var tasks = taskRepository.findAll();
        assertNotNull(tasks);
        assertEquals(2, tasks.size());
    }
}
