package ru.skypro.homework.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    private User user;
    private UserDTO userDTO;
    private UpdateUserDTO updateUserDTO;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(99);
        user.setPhone("91123455");
        user.setLastName("Ivanov");
        user.setFirstName("John");
        user.setImage("avatar.jpg");

        userDTO = new UserDTO();
        userDTO.setFirstName("John");
        userDTO.setPhone("91123455");

        updateUserDTO = new UpdateUserDTO();
        updateUserDTO.setFirstName("John");
        updateUserDTO.setPhone("91123455");
    }

    @Test
    void testToDtoUserDTO() {
        UserDTO mappedDto = userMapper.toDtoUserDTO(user);
        assertNotNull(mappedDto);
        assertEquals(user.getFirstName(), mappedDto.getFirstName());
        assertEquals(user.getPhone(), mappedDto.getPhone());
    }

    @Test
    void testToDtoUpdateUserDTO() {
        UpdateUserDTO mappedDto = userMapper.toDtoUpdateUserDTO(user);
        assertNotNull(mappedDto);
        assertEquals(user.getFirstName(), mappedDto.getFirstName());
        assertEquals(user.getPhone(), mappedDto.getPhone());
    }

    @Test
    void testToModel() {
        User mappedUser = userMapper.toModel(userDTO);
        assertNotNull(mappedUser);
        assertEquals(userDTO.getFirstName(), mappedUser.getFirstName());
        assertEquals(userDTO.getPhone(), mappedUser.getPhone());
    }
}