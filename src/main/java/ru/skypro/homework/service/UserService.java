package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import org.springframework.security.core.Authentication;

public interface UserService {
    ResponseEntity<Void> setPassword(NewPasswordDTO newPasswordDTO, Authentication authentication);

    ResponseEntity<UserDTO> getUserInfo(Authentication authentication);

    ResponseEntity<UpdateUserDTO> updateUser(UpdateUserDTO updateUserDTO, Authentication authentication);

    ResponseEntity<Void> updateUserAvatar(MultipartFile image);

}