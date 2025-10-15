package com.example.BackEndSpring.contorller.bookMyShow;

import com.example.BackEndSpring.model.bookMyShow.User;
import com.example.BackEndSpring.service.bookMyShow.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mybms/users")
public class BMSUserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{phoneNumber}")
    public ResponseEntity<User> getUserById(@PathVariable String phoneNumber) {
        User user = userService.getUserById(phoneNumber)
                .orElseThrow(() -> new RuntimeException("User not found with Phone Number: " + phoneNumber));
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{phoneNumber}")
    public ResponseEntity<User> updateUser(@PathVariable String phoneNumber, @RequestBody User userDetails) {
        User updateUser = userService.updateUser(phoneNumber, userDetails);
        return ResponseEntity.ok(updateUser);
    }

    @DeleteMapping("/{phoneNumber}")
    public ResponseEntity<Void> DeleteUser(@PathVariable String phoneNumber) {
        userService.deleteUser(phoneNumber);
        return ResponseEntity.noContent().build();
    }
}
