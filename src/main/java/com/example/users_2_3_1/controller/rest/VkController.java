package com.example.users_2_3_1.controller.rest;

import com.example.users_2_3_1.service.VkBotService;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VkController {
    @Autowired
    private VkBotService vkBotService;

    @GetMapping("/vkbot/{id}")
    public ResponseEntity<?> sendNotificationToBot(@PathVariable("id") Long id) throws ClientException, ApiException {
        vkBotService.sendMessageToChat(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
