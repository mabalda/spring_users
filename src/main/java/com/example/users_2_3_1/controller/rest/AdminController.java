package com.example.users_2_3_1.controller.rest;

import com.example.users_2_3_1.model.RequestDTO;
import com.example.users_2_3_1.model.User;
import com.example.users_2_3_1.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/admin/users")
    public ResponseEntity<List<User>> findAll() {
        List<User> users = adminService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/admin/users/requests")
    public ResponseEntity<List<User>> findUsersWhoWantToBeAdmin() {
        List<User> users = adminService.findUsersWhoWantToBeAdmin();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping(value= "/admin/users/new")
    public ResponseEntity<?> createNewUser(@RequestBody User user) {
        adminService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/admin/users/{id}")
    public ResponseEntity<User> getUserForm(@PathVariable("id") Long id) {
        User user = adminService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/admin/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        adminService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/admin/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody User updatedUser) {
        adminService.updateUser(updatedUser, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/admin/users/requests")
    public ResponseEntity<?> requestToApplication(@RequestBody RequestDTO request) {
        adminService.requestForApplication(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
