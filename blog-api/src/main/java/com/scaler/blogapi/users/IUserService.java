package com.scaler.blogapi.users;

import com.scaler.blogapi.users.dto.CreateUserDTO;
import com.scaler.blogapi.users.dto.LoginUserDTO;
import com.scaler.blogapi.users.dto.UserResponseDTO;

public interface IUserService {
    public UserResponseDTO createUser(CreateUserDTO createUserDTO);

    public UserResponseDTO loginUser(LoginUserDTO loginUserDTO);

}