package com.scaler.blogapi.users;

import com.scaler.blogapi.users.dto.CreateUserDTO;
import com.scaler.blogapi.users.dto.LoginUserDTO;
import com.scaler.blogapi.users.dto.UserResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UsersService implements IUserService {
    private final UsersRepository usersRepository;
    private final ModelMapper modelMapper;

    public UsersService(UsersRepository usersRepository, ModelMapper modelMapper) {
        this.usersRepository = usersRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public UserResponseDTO createUser(CreateUserDTO createUserDTO) {
        //TODO: Encrypt password
        //TODO: Validate email
        //TODO: Check if username already exists
        UserEntity newUserEntity = modelMapper.map(createUserDTO, UserEntity.class);
        UserEntity savedUser = usersRepository.save(newUserEntity);
        UserResponseDTO userResponseDTO = modelMapper.map(savedUser, UserResponseDTO.class);
        return userResponseDTO;
    }

    public UserResponseDTO loginUser(LoginUserDTO loginUserDTO) {
        UserEntity userEntity = usersRepository.findByUsername(loginUserDTO.getUsername());
        //TODO: Encrypt password
        if (userEntity == null) {
            throw new UserNotFoundException(loginUserDTO.getUsername());
        }
        if (!userEntity.getPassword().equals(loginUserDTO.getPassword())) {
            throw new IncorrectPasswordException();
        }

        UserResponseDTO userResponseDTO = modelMapper.map(userEntity,UserResponseDTO.class);
        return userResponseDTO;
    }

    public static class UserNotFoundException extends IllegalArgumentException {
        public UserNotFoundException(Integer id) {
            super("User with id " + id + " not found");
        }

        public UserNotFoundException(String username) {
            super("User with username " + username + " not found");
        }
    }

    public static class IncorrectPasswordException extends IllegalArgumentException {
        public IncorrectPasswordException() {
            super("Incorrect password");
        }
    }

}
