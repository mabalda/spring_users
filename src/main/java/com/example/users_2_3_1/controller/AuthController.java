package com.example.users_2_3_1.controller;

import com.example.users_2_3_1.model.User;
import com.example.users_2_3_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String main() {
        return "auth/main";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationForm(User user) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String registrateNewUser(User user) {
        userService.saveUser(user);

        return "redirect:/login";
    }
}
