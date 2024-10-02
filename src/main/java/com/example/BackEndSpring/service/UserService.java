package com.example.BackEndSpring.service;

import com.example.BackEndSpring.model.User;
import com.example.BackEndSpring.model.UserDTO;

public interface UserService {
    User findByUserName(String username);
    User save(UserDTO userDTO);
}
