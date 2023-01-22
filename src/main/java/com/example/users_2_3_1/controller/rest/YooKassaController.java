package com.example.users_2_3_1.controller.rest;

import com.example.users_2_3_1.model.YooKassaPostRequestDTO;
import com.example.users_2_3_1.service.YooKassaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class YooKassaController {
    @Autowired
    private YooKassaService yooKassaService;

    @GetMapping("/payment")
    public ResponseEntity<YooKassaPostRequestDTO> getToken(@RequestParam String value, @RequestParam String description) throws IOException {
        YooKassaPostRequestDTO result = yooKassaService.postMethod(value, description);
        return (result != null) ? new ResponseEntity<>(result, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
