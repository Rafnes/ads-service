package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Path;

public interface UserService {
    void updatePassword(NewPasswordDTO newPasswordDTO, Authentication authentication);

    UserDTO getUserInfo(Authentication authentication);

    UpdateUserDTO updateUser(UpdateUserDTO updateUserDTO, Authentication authentication);

    void updateUserAvatar(MultipartFile image, Authentication authentication);

    void downloadAvatarFromFileSystem(int id, HttpServletResponse response) throws IOException;

}