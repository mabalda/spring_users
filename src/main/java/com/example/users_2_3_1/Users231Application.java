package com.example.users_2_3_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages = {"com.example"})
@EnableCaching
public class Users231Application {

    public static void main(String[] args) {
        SpringApplication.run(Users231Application.class, args);
    }

}
