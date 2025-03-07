package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class CreateOrUpdateComment {
    @Size(min = 8, max = 64, message = "Длина комментария должна быть от 8 до 64 символов")
    private String text;
}
