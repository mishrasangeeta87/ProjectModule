package com.scaler.blogapi.commons;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Getter
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer id;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    Date modifiedDate;
}
