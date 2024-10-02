package com.example.BackEndSpring.repos;

import com.example.BackEndSpring.model.User;
import com.example.BackEndSpring.model.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String username);
    User save(UserDTO userDTO);
}
