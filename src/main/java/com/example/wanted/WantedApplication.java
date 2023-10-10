package com.example.wanted;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class WantedApplication {

    public static void main(String[] args) {
        SpringApplication.run(WantedApplication.class, args);
    }

}
