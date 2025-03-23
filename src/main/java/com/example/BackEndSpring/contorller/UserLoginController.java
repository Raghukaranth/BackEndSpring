package com.example.BackEndSpring.contorller;

import com.example.BackEndSpring.model.UserLoginDetails;
import com.example.BackEndSpring.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loginUser")
public class UserLoginController {
    @Autowired
    UserLoginService userLoginService;

    @PostMapping("/signup")
    public ResponseEntity<UserLoginDetails> signUpForTheApp(@RequestBody UserLoginDetails userLoginDetails) {
        try {
            UserLoginDetails userLoginObj = userLoginService.saveUserToDB(userLoginDetails);
            return new ResponseEntity<>(userLoginObj, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/login/{id}")
    public ResponseEntity<UserLoginDetails> loginToApp(@PathVariable Long id) throws Exception {
        try {
            UserLoginDetails userLoginObj = userLoginService.getUserFromId(id);
            return new ResponseEntity<>(userLoginObj, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
