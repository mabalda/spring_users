package com.example.users_2_3_1.controller.rest;

import com.example.users_2_3_1.service.DiscordBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiscordController {
    @Autowired
    private DiscordBotService discordBotService;

    @GetMapping("/discordbot/{id}")
    public ResponseEntity<?> sendNotificationToBot(@PathVariable("id") Long id) {
        discordBotService.sendMessageToChat(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
