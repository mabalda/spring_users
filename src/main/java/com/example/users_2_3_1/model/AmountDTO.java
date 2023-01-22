package com.example.users_2_3_1.model;

import lombok.Data;

@Data
public class AmountDTO {
    private String value;
    private final String currency = "RUB";
}
