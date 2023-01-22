package com.example.users_2_3_1.service;

import com.example.users_2_3_1.model.YooKassaPostRequestDTO;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Random;

@Service
public class YooKassaService {
    @Value("${yookassa.secretKey}")
    private String secretKey;
    @Value("${yookassa.shopId}")
    private String shopId;

    private final static String URL_TO_POST = "https://api.yookassa.ru/v3/payments";

    public YooKassaPostRequestDTO postMethod(String value, String description) throws IOException {
        YooKassaPostRequestDTO bodyContent = new YooKassaPostRequestDTO();
        bodyContent.getAmount().setValue(value);
        bodyContent.setDescription(description);

        String auth = shopId + ":" + secretKey;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.US_ASCII));
        String authHeader = "Basic " + new String(encodedAuth);

        Random random = new Random();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Idempotence-Key", String.valueOf(random.nextInt()));
        headers.add("Authorization", authHeader);
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<YooKassaPostRequestDTO> requestBody = new HttpEntity<>(bodyContent, headers);

        YooKassaPostRequestDTO response = restTemplate.postForObject(URL_TO_POST, requestBody, YooKassaPostRequestDTO.class);

        return response;
    }

}
