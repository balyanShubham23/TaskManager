package com.example.taskmanager.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Entity(name = "notes")
@Getter
@Setter
public class NoteEntity extends BaseEntity{

    public NoteEntity(String Body)
    {
        this.body = Body;
    }

    public NoteEntity()
    {
    }

    @Column(name = "body", nullable = false, length = 500)
    String body;

    @ManyToOne
    TaskEntity task;
}
