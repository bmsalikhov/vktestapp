package ru.bmsalikhov.vktestapp.services;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.bmsalikhov.vktestapp.config.CustomUserDetails;
import ru.bmsalikhov.vktestapp.models.User;
import ru.bmsalikhov.vktestapp.repos.UserRepository;

import java.util.Optional;

@Component
@NoArgsConstructor
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsernameEquals(username);
        System.out.println(user.isPresent());
        return user.map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }

    public void save(User user, boolean isNew) {
        if (isNew) {
            passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.save(user);
    }

    public boolean addUser(User user) {
        Optional<User> userFromDb = userRepository.findByUsernameEquals(user.getUsername());
        if (userFromDb.isPresent()) {
            return false;
        }
        user.setRole(user.getRole());
        save(user, true);
        return true;
    }
}

