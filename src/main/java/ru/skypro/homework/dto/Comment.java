package ru.skypro.homework.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class Comment {
    private Integer author;
    private String authorImage;
    private String authorFirstName;
    private Long createdAt;
    private Integer pk;
    private String text;

    public Comment() {
        this.createdAt = Instant.now().toEpochMilli();
    }
}
