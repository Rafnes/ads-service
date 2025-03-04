package ru.skypro.homework.controller;

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
public class UserController {
    @PostMapping("/set_password")
    public ResponseEntity<Void> setPassword(@Valid @RequestBody NewPassword newPassword) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getUserInfo() {
        return ResponseEntity.ok(new UserDTO());
    }

    @PatchMapping("/me")
    public ResponseEntity<UserCredentialsDTO> updateUserCredentials(@RequestBody UserCredentialsDTO userCredentialsDTO) {
        return ResponseEntity.ok(new UserCredentialsDTO());
    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE) 
    public ResponseEntity<Void> updateUserAvatar () {
        return ResponseEntity.ok().build();
    }
}
