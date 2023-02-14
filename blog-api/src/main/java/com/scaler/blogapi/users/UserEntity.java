package com.scaler.blogapi.users;

import com.scaler.blogapi.commons.BaseEntity;

import javax.persistence.Entity;

@Entity
public class UserEntity extends BaseEntity {
    String username;
    String password;//TODO
    String email;
    String image;
}
