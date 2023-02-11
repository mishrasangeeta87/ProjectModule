package com.example.springtaskmgr2.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "notes")
@Setter
@Getter
public class NoteEntity extends BaseEntity {
    @Column(name = "body", nullable = false, length = 500)
    String body;

    @ManyToOne
    TaskEntity task;
}
