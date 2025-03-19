package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;

public interface UserService {
    ResponseEntity<Void> setPassword(NewPasswordDTO newPasswordDTO);

    ResponseEntity<UserDTO> getUserInfo();

    ResponseEntity<UpdateUserDTO> updateUser(UpdateUserDTO updateUserDTO);

    ResponseEntity<Void> updateUserAvatar(MultipartFile image);

}
