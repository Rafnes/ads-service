package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

public interface UserService {
    ResponseEntity<Void> setPassword(NewPassword newPassword);

    ResponseEntity<User> getUserInfo();

    ResponseEntity<UpdateUser> updateUser(UpdateUser updateUser);

    ResponseEntity<Void> updateUserAvatar(MultipartFile image);

}
