package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "Ad")
@Data
public class AdDTO {
    private Integer author;
    private String image;
    private Integer pk;
    private Integer price;
    private String title;
}
