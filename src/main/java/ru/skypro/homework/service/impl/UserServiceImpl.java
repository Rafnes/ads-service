package ru.skypro.homework.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public ResponseEntity<Void> setPassword(NewPasswordDTO newPasswordDTO) {
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<UserDTO> getUserInfo() {
        return ResponseEntity.ok(new UserDTO());
    }

    @Override
    public ResponseEntity<UpdateUserDTO> updateUser(UpdateUserDTO updateUserDTO) {
        return ResponseEntity.ok(new UpdateUserDTO());
    }

    @Override
    public ResponseEntity<Void> updateUserAvatar(MultipartFile image) {
        return ResponseEntity.noContent().build();
    }
}
