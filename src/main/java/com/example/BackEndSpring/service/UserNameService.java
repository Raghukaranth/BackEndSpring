package com.example.BackEndSpring.service;

import com.example.BackEndSpring.model.UserNameModel;
import com.example.BackEndSpring.repository.UserNameRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserNameService {

    @Autowired
    UserNameRepository userNameRepository;

    public UserNameModel getUserNameRepository(String name) {
        return userNameRepository.getReferenceById(name);
    }

    public UserNameModel saveUserName(String name) throws Exception {
        return userNameRepository.findById(name)
                .orElseThrow(() -> new Exception(name + " not found"));
    }
}
