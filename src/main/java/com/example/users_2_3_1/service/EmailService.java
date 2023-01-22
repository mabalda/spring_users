package com.example.users_2_3_1.service;

import com.example.users_2_3_1.model.Role;
import com.example.users_2_3_1.model.User;
import com.example.users_2_3_1.repository.RoleRepository;
import com.example.users_2_3_1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public void sendEmailToAdmins(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        String username = user.getUsername();

        Role adminRole = roleRepository.findByRole("ROLE_ADMIN");
        List<User> admins = userRepository.findAll().stream().filter(u -> u.getRoles().contains(adminRole)).toList();

        String mailText = "User " + username + " wants to become admin. User's id is " + userId;
        String mailSubject = "User want to be admin";

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("mbalda98@yandex.ru");
        simpleMailMessage.setSubject(mailSubject);
        simpleMailMessage.setText(mailText);

        for (User admin : admins) {
            simpleMailMessage.setTo(admin.getEmail());
            emailSender.send(simpleMailMessage);
        }
    }
}
