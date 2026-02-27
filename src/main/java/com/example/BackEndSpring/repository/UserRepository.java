package com.example.BackEndSpring.repository;

import com.example.BackEndSpring.model.Employee;
import com.example.BackEndSpring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}
