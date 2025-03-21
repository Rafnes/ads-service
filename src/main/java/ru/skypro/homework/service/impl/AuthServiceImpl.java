package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterDTO;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.encoder = passwordEncoder;
    }

    @Override
    public boolean login(String userName, String password) {
        return userRepository.findByEmail(userName)
                .map(user -> encoder.matches(password, user.getPassword()))
                .orElse(false);
    }

    @Override
    public boolean register(RegisterDTO registerDTO) {
        if (userRepository.findByEmail(registerDTO.getUsername()).isPresent()) {
            return false;
        }
        if (registerDTO.getPassword().length() < 8) {
            throw new IllegalArgumentException("Пароль должен быть длиннее 8 символов");
        }
        log.info(registerDTO.toString());
        User user = new User();
        user.setEmail(registerDTO.getUsername());
        user.setPassword(encoder.encode(registerDTO.getPassword()));
        user.setRole(Role.valueOf(registerDTO.getRole().name()));
        user.setEnabled(true);
        log.info(user.toString());

        userRepository.save(user);
        return true;
    }

}
