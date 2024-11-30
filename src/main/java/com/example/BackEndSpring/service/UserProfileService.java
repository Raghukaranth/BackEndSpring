package com.example.BackEndSpring.service;

import com.example.BackEndSpring.model.Employee;
import com.example.BackEndSpring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileService {
    @Autowired
    private UserRepository userRepository;

    public Employee saveEmployee(Employee employee){
        return  userRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return userRepository.findAll();
    }


}
