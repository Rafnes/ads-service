package ru.skypro.homework.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    private final UserRepository userRepository;

    public UserDetailsServiceImpl (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Вошли в loadUserByUsername");

        return userRepository.findByEmail(username)
                .map(user -> {
                    log.info("Преобразуем User в SecurityUser для пользователя {}", user.getEmail());
                    return new SecurityUser(user);
                })
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}
