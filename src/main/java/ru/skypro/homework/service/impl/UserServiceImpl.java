package ru.skypro.homework.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.model.User;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.encoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<Void> setPassword(NewPasswordDTO newPasswordDTO, Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setPassword(encoder.encode(newPasswordDTO.getNewPassword()));
        userRepository.save(user);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<UserDTO> getUserInfo(Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return ResponseEntity.ok(userMapper.toDtoUserDTO(user));
    }

    @Override
    public ResponseEntity<UpdateUserDTO> updateUser(UpdateUserDTO updateUserDTO, Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setFirstName(updateUserDTO.getFirstName());
        user.setPhone(updateUserDTO.getPhone());
        user.setLastName(updateUserDTO.getLastName());
        userRepository.save(user);
        return ResponseEntity.ok(userMapper.toDtoUpdateUserDTO(user));
    }

    @Override
    public ResponseEntity<Void> updateUserAvatar(MultipartFile image) {
        return ResponseEntity.noContent().build();
    }
}
