package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

import javax.validation.Valid;

@RestController
@Tag(name = "Пользователи")
@RequestMapping("/users")
@Tag(name = "Пользователи", description = "Методы для управления пользователями и их данными")
public class UserController {

    @Operation(summary = "Сменить пароль", description = "Позволяет пользователю сменить пароль")
    @PostMapping("/set_password")
    public ResponseEntity<Void> setPassword(@Valid @RequestBody NewPassword newPassword) {
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Получить информацию о пользователе", description = "Возвращает данные текущего авторизованного пользователя")
    @GetMapping("/me")
    public ResponseEntity<User> getUserInfo() {
        return ResponseEntity.ok(new User());
    }

    @Operation(summary = "Обновить данные пользователя", description = "Позволяет изменить имя, фамилию или другие данные пользователя")
    @PatchMapping("/me")
    public ResponseEntity<UpdateUser> updateUser(@RequestBody UpdateUser updateUser) {
        return ResponseEntity.ok(new UpdateUser());
    }

    @Operation(summary = "Обновить аватар пользователя", description = "Позволяет загрузить или заменить аватар пользователя")
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE) 
    public ResponseEntity<Void> updateUserAvatar () {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
