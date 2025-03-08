package com.example.BackEndSpring.contorller;

import com.example.BackEndSpring.model.UserNameModel;
import com.example.BackEndSpring.service.UserNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userName")
public class UserNameController {
    @Autowired
    UserNameService userNameService;

    @GetMapping("/{name}")
    public ResponseEntity<UserNameModel> getUserName(@PathVariable String name) {
        try {
            UserNameModel userNameModelObj = userNameService.getUserNameRepository(name);
            return new ResponseEntity<>(userNameModelObj, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{name}")
    public ResponseEntity<UserNameModel> PostUserName(@PathVariable String name) {
        try {
            UserNameModel userNameModelObj = userNameService.saveUserName(name);
            return new ResponseEntity<>(userNameModelObj, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

