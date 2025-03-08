package com.example.BackEndSpring.repository;

import com.example.BackEndSpring.model.UserNameModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserNameRepository extends JpaRepository<UserNameModel, String> {
}
