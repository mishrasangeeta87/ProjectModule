package com.example.springtaskmgr2.entities;


import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Integer id;

    @CreatedDate
    @Column(name="created_at",nullable = false)
    private Date createdAt;

    public Integer getId() {
        return id;
    }
}
