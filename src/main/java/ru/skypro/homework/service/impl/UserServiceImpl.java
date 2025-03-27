package ru.skypro.homework.service.impl;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.exception.UserNotFoundException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;
    private final ImageService imageService;

    public UserServiceImpl(ImageRepository imageRepository, UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, ImageService imageService) {
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
        this.userMapper = userMapper;
        this.encoder = passwordEncoder;
        this.imageService = imageService;
    }

    @Override
    public void updatePassword(NewPasswordDTO newPasswordDTO, Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setPassword(encoder.encode(newPasswordDTO.getNewPassword()));
        userRepository.save(user);
    }

    @Override
    public UserDTO getUserInfo(Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new UserNotFoundException(authentication.getName()));

        UserDTO userDTO = userMapper.toDtoUserDTO(user);
        if (userDTO.getImage() != null) {
            userDTO.setImage("/users/me/image/" + userDTO.getImage() + "/get");
        }
        return userDTO;
    }

    @Override
    public UpdateUserDTO updateUser(UpdateUserDTO updateUserDTO, Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setFirstName(updateUserDTO.getFirstName());
        user.setPhone(updateUserDTO.getPhone());
        user.setLastName(updateUserDTO.getLastName());
        userRepository.save(user);
        return userMapper.toDtoUpdateUserDTO(user);
    }

    @Override
    public void updateUserAvatar(MultipartFile image, Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        int id = 0;
        if (user.getImage() != null) {
            id = user.getImage().getId();
        }
        try {
            Image imageObj = imageService.addImage(id, image);
            user.setImage(imageObj);
            userRepository.save(user);
        } catch (IOException e) {
            System.out.println("Ошибка записи файла");
        }
    }

    public void downloadAvatarFromFileSystem(int studentId, HttpServletResponse response)
            throws IOException {

        Image avatarOpt = imageRepository.findById(studentId).orElseThrow(() -> new UsernameNotFoundException("User not found"));


        Path path = Path.of(avatarOpt.getFilePath());
        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream();) {
            response.setStatus(200);
            response.setContentType("image/jpg");
            response.setContentLength((int) avatarOpt.getFileSize());
            is.transferTo(os);
        }
    }
}
