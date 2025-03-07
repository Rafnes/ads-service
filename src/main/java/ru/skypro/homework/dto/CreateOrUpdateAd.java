package ru.skypro.homework.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CreateOrUpdateAd {
    private String title;
    private Integer price;
    private String description;
}
