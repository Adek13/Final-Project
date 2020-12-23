package com.mBank.RestAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.mBank.RestAPI"})
public class StartAPI {

    public static void main(String[] args) {
        SpringApplication.run(StartAPI.class, args);
    }

}
