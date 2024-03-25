package com.ortega.tshomboapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class TshomboApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TshomboApiApplication.class, args);
    }

}
