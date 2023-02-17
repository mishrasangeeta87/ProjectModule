package com.scaler.blogapi.users.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {
    Integer id;
    String email;
    String username;
    String bio;
    String image;
}
