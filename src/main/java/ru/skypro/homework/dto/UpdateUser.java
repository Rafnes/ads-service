package ru.skypro.homework.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UpdateUser {
    private String firstName;
    private String lastName;
    private String phone;
}
