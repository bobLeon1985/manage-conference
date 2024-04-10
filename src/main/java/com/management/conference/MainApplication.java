package com.management.conference;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

import java.io.IOException;

@SpringBootApplication
@EnableWebFlux
public class MainApplication {



    public static void main(String[] args) throws IOException {
        SpringApplication.run(MainApplication.class, args);
    }
}