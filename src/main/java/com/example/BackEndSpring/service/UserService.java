package com.example.BackEndSpring.service;

import com.example.BackEndSpring.model.User;
import com.example.BackEndSpring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(String username, String password, String email) {
        if(userRepository.existsByUsername(username))
            throw new RuntimeException("Username already exists");
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setRole("USER");
        user.setEnabled(true);
        return userRepository.save(user);
    }

    public Optional<User> findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean validateUser(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.isPresent() && passwordEncoder.matches(password, user.get().getPassword());
    }
}
