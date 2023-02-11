package com.example.springtaskmgr2.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity(name = "notes")
public class NoteEntity extends BaseEntity{
   @Column(name = "body",nullable = false,length = 500)
    String body;

}
