package com.example.users_2_3_1.controller.rest;

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

    @PostMapping(value= "/admin/users/new")
    public ResponseEntity<?> createNewUser(@RequestBody User user) {
        userService.saveUser(user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/admin/delete_user/{id}")
    public ResponseEntity<User> userForDelete(@PathVariable("id") Long id) {
        User userToBeUpdated = userService.findById(id);
        return new ResponseEntity<>(userToBeUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/admin/delete_user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/admin/update_user/{id}")
    public ResponseEntity<User> userForUpdate(@PathVariable("id") Long id) {
        User userToBeUpdated = userService.findById(id);
        return new ResponseEntity<>(userToBeUpdated, HttpStatus.OK);
    }

    @PutMapping("/admin/update_user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody User updatedUser) {
        userService.updateUser(updatedUser, id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
