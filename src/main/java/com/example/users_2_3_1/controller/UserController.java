package com.example.users_2_3_1.controller;

import com.example.users_2_3_1.model.User;
import com.example.users_2_3_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/admin/users")
    public ResponseEntity<List<User>> findAll(Model model) {
        List<User> users = userService.findAll();

        return users != null && !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/user")
    public ResponseEntity<User> showUser(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

//    @GetMapping("/admin/users/new")
//    public String newUserForm(User user) {
//        return "new_user";
//    }

    @PostMapping("/admin/users/new")
    public ResponseEntity<?> createNewUser(@RequestBody User user) {
        userService.saveUser(user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/delete_user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping("/admin/update_user/{id}")
//    public String updateUserForm(@PathVariable("id") Long id, Model model) {
//        User userToBeUpdated = userService.findById(id);
//        model.addAttribute("user", userToBeUpdated);
//        return "update_user";
//    }

    @PutMapping("/admin/update_user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody User updatedUser) {
        userService.updateUser(updatedUser, id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
