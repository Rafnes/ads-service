package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UserAvatarDTO;
import ru.skypro.homework.dto.UserCredentialsDTO;
import ru.skypro.homework.dto.UserDTO;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@Tag(name = "Пользователи", description = "Методы для управления пользователями и их данными")
public class UserController {

    @Operation(summary = "Сменить пароль", description = "Позволяет пользователю сменить пароль")
    @PostMapping("/set_password")
    public ResponseEntity<Void> setPassword(@Valid @RequestBody NewPassword newPassword) {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Получить информацию о пользователе", description = "Возвращает данные текущего авторизованного пользователя")
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getUserInfo() {
        return ResponseEntity.ok(new UserDTO());
    }

    @Operation(summary = "Обновить данные пользователя", description = "Позволяет изменить имя, фамилию или другие данные пользователя")
    @PatchMapping("/me")
    public ResponseEntity<UserCredentialsDTO> updateUserCredentials(@RequestBody UserCredentialsDTO userCredentialsDTO) {
        return ResponseEntity.ok(new UserCredentialsDTO());
    }

    @Operation(summary = "Обновить аватар пользователя", description = "Позволяет загрузить или заменить аватар пользователя")
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE) 
    public ResponseEntity<Void> updateUserAvatar () {
        return ResponseEntity.ok().build();
    }
}
