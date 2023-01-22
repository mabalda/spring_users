package com.example.users_2_3_1.controller.rest;

import com.example.users_2_3_1.model.User;
import com.example.users_2_3_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public ResponseEntity<User> showUser() {
        User user = userService.getCurrentUser();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/password")
    public ResponseEntity<?> resetPassword(@RequestBody User user) {
        userService.resetPassword(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/user/request")
    public ResponseEntity<?> changeRequestForAdmin() {
        userService.changeRequestForAdmin();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
