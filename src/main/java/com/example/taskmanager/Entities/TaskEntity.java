package com.example.taskmanager.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity(name = "tasks")
@Getter
@Setter
public class TaskEntity extends BaseEntity{

    public TaskEntity(String title, String Desc, Boolean Comp, Date update)
    {
        this.title = title;
        this.description = Desc;
        this.completed = Comp;
        this.dueDate = update;
    }

    public TaskEntity()
    {

    }

    @Column(name = "title", nullable = false, length = 150)
    String title;

    @Column(name = "description", nullable = false , length = 500)
    String description;

    @Column(name="completed", nullable = false, columnDefinition = "boolean default false")
    Boolean completed;

    @Column(name="due_date", nullable = true)
    Date dueDate;

    @JsonManagedReference
    @OneToMany(mappedBy = "task")
    List<NoteEntity> noteEntityList;
}
