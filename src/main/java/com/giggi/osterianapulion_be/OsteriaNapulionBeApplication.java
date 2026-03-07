package com.giggi.osterianapulion_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class OsteriaNapulionBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(OsteriaNapulionBeApplication.class, args);
    }

}
