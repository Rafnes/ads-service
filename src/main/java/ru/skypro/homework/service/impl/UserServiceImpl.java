package ru.skypro.homework.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public ResponseEntity<Void> setPassword(NewPassword newPassword) {
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<User> getUserInfo() {
        return ResponseEntity.ok(new User());
    }

    @Override
    public ResponseEntity<UpdateUser> updateUser(UpdateUser updateUser) {
        return ResponseEntity.ok(new UpdateUser());
    }

    @Override
    public ResponseEntity<Void> updateUserAvatar(MultipartFile image) {
        return ResponseEntity.noContent().build();
    }
}
