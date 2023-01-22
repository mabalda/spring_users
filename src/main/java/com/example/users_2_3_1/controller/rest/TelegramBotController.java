package com.example.users_2_3_1.controller.rest;

import com.example.users_2_3_1.model.TelegramCodeDTO;
import com.example.users_2_3_1.service.TelegramBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@RestController
public class TelegramBotController {
    @Autowired
    private TelegramBotService telegramBotService;

    @GetMapping("/code")
    public ResponseEntity<?> sendAuthCodeToBot(@RequestParam String telegramUsername) throws TelegramApiException {
        return telegramBotService.sendAuthorizationCode(telegramUsername) ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/code")
    public ResponseEntity<?> checkAuthCode(@RequestBody TelegramCodeDTO dto) {
        return telegramBotService.checkAuthorizationCodes(dto) ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
