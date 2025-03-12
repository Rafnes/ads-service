package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.service.UserService;

import javax.validation.Valid;

@RestController
@Tag(name = "Пользователи")
@RequestMapping("/users")
@Tag(name = "Пользователи", description = "Методы для управления пользователями и их данными")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Сменить пароль", description = "Позволяет пользователю сменить пароль")
    @PostMapping("/set_password")
    public ResponseEntity<Void> setPassword(@Valid @RequestBody NewPasswordDTO newPasswordDTO) {
        return userService.setPassword(newPasswordDTO);
    }

    @Operation(summary = "Получить информацию о пользователе", description = "Возвращает данные текущего авторизованного пользователя")
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getUserInfo() {
        return userService.getUserInfo();
    }

    @Operation(summary = "Обновить данные пользователя", description = "Позволяет изменить имя, фамилию или другие данные пользователя")
    @PatchMapping("/me")
    public ResponseEntity<UpdateUserDTO> updateUser(@RequestBody UpdateUserDTO updateUserDTO) {
        return userService.updateUser(updateUserDTO);
    }

    @Operation(summary = "Обновить аватар пользователя", description = "Позволяет загрузить или заменить аватар пользователя")
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateUserAvatar(@RequestPart("image") MultipartFile image) {
        return userService.updateUserAvatar(image);
    }
}
