package com.example.springtaskmgr2.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

import java.util.Date;

@Entity(name = "tasks")
@Table(indexes = @Index(columnList = "title"))
public class TaskEntity extends BaseEntity {

    @Column(name = "title", nullable = false, length = 155)
    String title;

    @Column(name = "description", nullable = true, length = 500)
    String description;

    @Column(name = "completed", nullable = false, columnDefinition = "boolean default")
    Boolean completed = false;

    @Column(name = "due_date", nullable = true)
    Date dueDate;
}
