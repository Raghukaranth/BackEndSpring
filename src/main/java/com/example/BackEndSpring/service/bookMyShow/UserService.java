package com.example.BackEndSpring.service.bookMyShow;

import com.example.BackEndSpring.model.bookMyShow.User;
import com.example.BackEndSpring.repository.bookMyShow.BMSUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    BMSUserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String phoneNumber) {
        return userRepository.findById(phoneNumber);
    }

    public User updateUser(String phoneNumber, User userDetails) {
        User user = userRepository.findById(phoneNumber).orElseThrow(
                () -> new RuntimeException("User not found With Id" + phoneNumber));

        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setPhone(userDetails.getPhone());

        return userRepository.save(user);
    }

    public void deleteUser(String phoneNumber) {
        User user = userRepository.findById(phoneNumber).orElseThrow(
                () -> new RuntimeException("User not found With Id" + phoneNumber));

        userRepository.delete(user);
    }
}
