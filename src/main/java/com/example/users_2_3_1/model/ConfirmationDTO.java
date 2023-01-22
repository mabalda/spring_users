package com.example.users_2_3_1.model;

import lombok.Data;

@Data
public class ConfirmationDTO {
    private final String type = "embedded";
    private String confirmation_token;
}
