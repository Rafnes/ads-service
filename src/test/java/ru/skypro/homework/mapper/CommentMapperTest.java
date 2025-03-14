package ru.skypro.homework.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;

import static org.junit.jupiter.api.Assertions.*;

public class CommentMapperTest {
    private final CommentMapper commentMapper = Mappers.getMapper(CommentMapper.class);
    @Test
    void test1() {
        //given
        User author = new User();
        author.setId(1);
        author.setFirstName("John");
        author.setImage("avatar.jpg");

        Ad ad = new Ad();
        ad.setId(10);

        Comment comment = new Comment();
        comment.setId(2);
        comment.setAd(ad);
        comment.setAuthor(author);

        // test
        CommentDTO dto = commentMapper.toDtoCommentDTO(comment);
        System.out.println(dto);
        System.out.println(comment.getAd().getId());
        // check
        assertNotNull(dto);
        assertEquals(comment.getAd().getId(), dto.getPk());
        assertEquals(comment.getAuthor().getId(), dto.getAuthor());
        assertEquals(comment.getAuthor().getImage(), dto.getAuthorImage());
        assertEquals(comment.getText(), dto.getText());
    }
}