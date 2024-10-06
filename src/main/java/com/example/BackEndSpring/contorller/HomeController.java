package com.example.BackEndSpring.contorller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/react")
public class HomeController {
    @GetMapping
    public String Home(){
        return "Welcome To ToDo App";
    }
}
