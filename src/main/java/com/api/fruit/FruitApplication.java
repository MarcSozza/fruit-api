package com.api.fruit;

import com.api.fruit.config.CustomProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FruitApplication implements CommandLineRunner {

    @Autowired
    public CustomProperties customProperties;

    public static void main(String[] args) {
        SpringApplication.run(FruitApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Lien de la doc: " + customProperties.getDocUrl());
    }
}
