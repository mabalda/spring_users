package com.example.users_2_3_1.controller;

import com.example.users_2_3_1.model.User;
import com.example.users_2_3_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/users")
    public String findAll(Model model) {
        List<User> users = userService.findAll();

        model.addAttribute("users", users);

        return "all_users";
    }

    @GetMapping("/user")
    public String showUser(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        model.addAttribute("user", user);

        return "show_user";
    }

    @GetMapping("/admin/users/new")
    public String newUserForm(User user) {
        return "new_user";
    }

    @PostMapping("/admin/users/new")
    public String createNewUser(User user) {
        userService.saveUser(user);

        return "redirect:/admin/users";
    }

    @GetMapping("/admin/delete_user/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);

        return "redirect:/admin/users";
    }

    @GetMapping("/admin/update_user/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        User userToBeUpdated = userService.findById(id);
        model.addAttribute("user", userToBeUpdated);
        return "update_user";
    }

    @PostMapping("/update_user")
    public String updateUser(User updatedUser) {
        userService.updateUser(updatedUser);

        return "redirect:/admin/users";
    }
}
