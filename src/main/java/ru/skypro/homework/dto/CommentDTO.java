package ru.skypro.homework.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentDTO {
    private Integer author;
    private String authorImage;
    private String authorFirstName;
    private Long createdAt;
    private Integer pk;
    private String text;

}
