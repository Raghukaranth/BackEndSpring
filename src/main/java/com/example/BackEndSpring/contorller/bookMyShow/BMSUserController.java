package com.example.BackEndSpring.contorller.bookMyShow;

import com.example.BackEndSpring.model.bookMyShow.User;
import com.example.BackEndSpring.service.bookMyShow.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

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



    private final Map<HttpMethod, BiFunction<String, User, ResponseEntity<?>>> httpMethods = new HashMap();

    public BMSUserController() {
        httpMethods.put(HttpMethod.GET, this::getUserById);
        httpMethods.put(HttpMethod.PUT, this::updateUser);
        httpMethods.put(HttpMethod.DELETE, this::DeleteUser);
    }


    @RequestMapping(value = "/{phoneNumber}", method = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
    public ResponseEntity<?> handleUserAction(
            @PathVariable String phoneNumber,
            @RequestBody(required = false) User userDetails,
            HttpMethod method
    ) {
        BiFunction<String, User, ResponseEntity<?>> handler = httpMethods.get(method);

        if(handler == null) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                    .body("Unsupported Method" + method);
        }
        return  handler.apply(phoneNumber, userDetails);
    }

    public ResponseEntity<?> getUserById(String phoneNumber, User user) {
        return userService.getUserById(phoneNumber)
                .<ResponseEntity<?>> map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("user not found with this Phone number: " + phoneNumber));
    }

    public ResponseEntity<?> updateUser(String phoneNumber, User userDetails) {
        if(userDetails == null) {
            return ResponseEntity.badRequest().body("user details are required for updating");
        }
        User updateUser = userService.updateUser(phoneNumber, userDetails);
        return ResponseEntity.ok(updateUser);
    }

    public ResponseEntity<?> DeleteUser(String phoneNumber, User user) {
        userService.deleteUser(phoneNumber);
        return ResponseEntity.noContent().build();
    }
}
