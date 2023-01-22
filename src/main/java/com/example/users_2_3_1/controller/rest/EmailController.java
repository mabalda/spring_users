package com.example.users_2_3_1.controller.rest;

import com.example.users_2_3_1.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @Autowired
    private EmailService emailService;

    @GetMapping("/email/{id}")
    public ResponseEntity<?> sendEmailToAdmins(@PathVariable("id") Long id) {
        emailService.sendEmailToAdmins(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
