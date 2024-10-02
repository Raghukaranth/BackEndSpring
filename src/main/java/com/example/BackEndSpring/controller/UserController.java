package com.example.BackEndSpring.controller;

import com.example.BackEndSpring.model.User;
import com.example.BackEndSpring.repos.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping("/signup")
    public String getString() {
        return "signup";
    }

    @RequestMapping("/login")
    public String getLogin()
    {
        return "login";
    }

    @PostMapping("/addUser")
    public ModelAndView addUser(@RequestParam("user_email") String user_email, User user)
    {
        ModelAndView mv=new ModelAndView("success");
        List<User> list=userRepository.findByEMAIL(user_email);

        if(list.size()!=0)
        {
            mv.addObject("message", "Oops!  There is already a user registered with the email provided.");

        }
        else
        {
            userRepository.save(user);
            mv.addObject("message","User has been successfully registered.");
        }

        return mv;
    }
    @GetMapping("/dummy")
    public String dummy()
    {
        return "dummy";
    }

    @PostMapping("/login")
    public String login_user(@RequestParam("username") String username, @RequestParam("password") String password,
                             HttpSession session, ModelMap modelMap)
    {

        User auser=userRepository.findByUsernamePassword(username, password);

        if(auser!=null)
        {
            String uname=auser.getUser_email();
            String upass=auser.getUser_pass();

            if(username.equalsIgnoreCase(uname) && password.equalsIgnoreCase(upass))
            {
                session.setAttribute("username",username);
                return "dummy";
            }
            else
            {
                modelMap.put("error", "Invalid Account");
                return "login";
            }
        }
        else
        {
            modelMap.put("error", "Invalid Account");
            return "login";
        }

    }

    @GetMapping(value = "/logout")
    public String logout_user(HttpSession session)
    {
        session.removeAttribute("username");
        session.invalidate();
        return "redirect:/login";
    }
}
