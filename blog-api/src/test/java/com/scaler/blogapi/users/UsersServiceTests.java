package com.scaler.blogapi.users;

import com.scaler.blogapi.users.dto.CreateUserDTO;
import com.scaler.blogapi.users.dto.UserProfileDTO;
import com.scaler.blogapi.users.dto.UserResponseDTO;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UsersServiceTests {
    @Autowired
    private UsersRepository usersRepository;
    private UsersService usersService;

    private UsersService createUsersService() {
        if (usersService == null) {
            ModelMapper modelMapper = new ModelMapper();
            usersService = new UsersService(usersRepository, modelMapper);
        }
        return usersService;
    }

    @Test
    public void testCreateUser() {
        UsersService usersService = createUsersService();
        CreateUserDTO newUserDTO = new CreateUserDTO();
        newUserDTO.setEmail("san@gmail.com");
        newUserDTO.setPassword("pass");
        newUserDTO.setUsername("Sangeeta");
        UserResponseDTO userResponseDTO = usersService.createUser(newUserDTO);
        assertNotNull(userResponseDTO);
    }

    @Test
    public void getUserProfiles() {
        UsersService usersService = createUsersService();

        CreateUserDTO user1 = new CreateUserDTO();
        user1.setEmail("san@gmail.com");
        user1.setPassword("pass");
        user1.setUsername("Sangeeta");
        usersService.createUser(user1);

        CreateUserDTO user2 = new CreateUserDTO();
        user2.setEmail("sanm@gmail.com");
        user2.setPassword("passw");
        user2.setUsername("Sangu");
        usersService.createUser(user2);

        List<UserProfileDTO> profileDTOS = usersService.getUserProfiles();
        assertEquals(2, profileDTOS.size());

    }

    @Test
    public void getUserProfileByUserName() {
        UsersService usersService = createUsersService();

        CreateUserDTO user1 = new CreateUserDTO();
        user1.setEmail("san@gmail.com");
        user1.setPassword("pass");
        user1.setUsername("Sangeeta");
        usersService.createUser(user1);


        UserProfileDTO userProfileDTO = usersService.getProfileByUserName("Sangeeta");
        assertEquals("Sangeeta",userProfileDTO.getUsername());
    }

    @Test
    public void getUserProfileByUserNameNonExistent() {
        UsersService usersService = createUsersService();
        assertThrows(UsersService.UserNotFoundException.class,()->usersService.getProfileByUserName("Sangeeta"));
    }
}
