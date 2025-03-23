package com.example.BackEndSpring.service;

import com.example.BackEndSpring.model.UserLoginDetails;
import com.example.BackEndSpring.repository.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService {
    @Autowired
    UserLoginRepository userLoginRepository;

    public UserLoginDetails saveUserToDB(UserLoginDetails userLoginDetails) {
        return userLoginRepository.save(userLoginDetails);
    }

    public UserLoginDetails getUserFromId(Long id) throws Exception {
        return userLoginRepository.findById(id)
                .orElseThrow(() -> new Exception("user is not found"));
    }
}
