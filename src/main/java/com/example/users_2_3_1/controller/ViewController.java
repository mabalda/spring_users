package com.example.users_2_3_1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/user_page")
    public String getUserPage() {
        return "show_user";
    }

    @GetMapping("/admin_page")
    public String getAdminPage() {
        return "admin_page";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }
}
