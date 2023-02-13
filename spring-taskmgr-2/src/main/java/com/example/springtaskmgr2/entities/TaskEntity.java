package com.example.springtaskmgr2.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity(name = "tasks")
@Table(indexes = @Index(columnList = "title"))
@Setter
@Getter
public class TaskEntity extends BaseEntity {

    @Column(name = "title", nullable = false, length = 155)
    String title;

    @Column(name = "description", nullable = true, length = 500)
    String description;

    @Column(name = "completed", nullable = false, columnDefinition = "BOOLEAN DEFAULT")
    Boolean completed = false;

    @Column(name = "due_date", nullable = true)
    Date dueDate;

    @OneToMany(mappedBy = "task")
    List<NoteEntity> notes;
}
