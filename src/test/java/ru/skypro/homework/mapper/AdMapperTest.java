package ru.skypro.homework.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;

import static org.junit.jupiter.api.Assertions.*;

public class AdMapperTest {
    private final AdMapper adMapper = Mappers.getMapper(AdMapper.class);
    @Test
    void test2() {
        //given
        User author = new User();
        author.setId(8);
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
        AdDTO dto = adMapper.toDtoAdDTO(ad);
        System.out.println(dto);
        System.out.println(ad.getAuthor().getId());
        // check
        assertNotNull(dto);

        assertEquals(ad.getAuthor().getId(), dto.getAuthor());
        assertEquals(ad.getId(), dto.getPk());
        assertEquals(ad.getTitle(), dto.getTitle());
        assertEquals(ad.getPrice(), dto.getPrice());

        Ad modelAd = adMapper.toModel(dto);
        System.out.println(modelAd.getId());
    }
}
