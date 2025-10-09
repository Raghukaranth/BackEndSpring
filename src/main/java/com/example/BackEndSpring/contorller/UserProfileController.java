package com.example.BackEndSpring.contorller;

import com.example.BackEndSpring.model.Employee;
import com.example.BackEndSpring.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserProfileController {
    @Autowired
    private UserProfileService userProfileService;

    @PostMapping("/add")
    public Employee addUsers(@RequestBody Employee employee) {
        return userProfileService.saveEmployee(employee);
    }

    @GetMapping("/")
        public List<Employee> getAllEmployees() {
            return userProfileService.getAllEmployees();
        }
}

