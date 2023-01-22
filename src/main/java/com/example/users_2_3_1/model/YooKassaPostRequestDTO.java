package com.example.users_2_3_1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class YooKassaPostRequestDTO {
    private AmountDTO amount;
    private ConfirmationDTO confirmation;
    private final String capture = "true";
    private String description;

    public YooKassaPostRequestDTO() {
        amount = new AmountDTO();
        confirmation = new ConfirmationDTO();
    }
}
