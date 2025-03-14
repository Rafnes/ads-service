package ru.skypro.homework.mapper;

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

    @Test
    void test2() {
        //given
        User author = new User();
        author.setId(99);
        author.setPhone("91123455");
        author.setLastName("Ivanov");
        author.setFirstName("John");
        author.setImage("avatar.jpg");

        Ad ad = new Ad();
        ad.setId(6);
        ad.setTitle("Объявление 1");
        ad.setPrice(100);
        ad.setAuthor(author);

        Comment comment = new Comment();
        comment.setId(2);
        comment.setAd(ad);
        comment.setAuthor(author);
        // test
        UserDTO dto = userMapper.toDtoUserDTO(author);
        System.out.println(dto);
        System.out.println(author.getId());
        // check
        assertNotNull(dto);

        assertEquals(author.getFirstName(), dto.getFirstName());
        assertEquals(author.getPhone(), dto.getPhone());

        UpdateUserDTO dto2 = userMapper.toDtoUpdateUserDTO(author);
        System.out.println(dto2);

        User modelUser = userMapper.toModel(dto);
        System.out.println(modelUser.getId());
    }
}