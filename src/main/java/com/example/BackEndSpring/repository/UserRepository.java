package com.example.BackEndSpring.repository;

import com.example.BackEndSpring.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Employee, Long> {
}
