package com.example.BackEndSpring.service.bookMyShow;

import com.example.BackEndSpring.model.bookMyShow.User;
import com.example.BackEndSpring.repository.bookMyShow.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {
    UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found With Id" + id));

        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setPhone(userDetails.getPhone());

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found With Id" + id));

        userRepository.delete(user);
    }
}
