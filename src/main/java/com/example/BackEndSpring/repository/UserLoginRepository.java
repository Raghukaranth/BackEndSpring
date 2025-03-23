package com.example.BackEndSpring.repository;

import com.example.BackEndSpring.model.UserLoginDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoginRepository extends JpaRepository<UserLoginDetails, Long> {
}
