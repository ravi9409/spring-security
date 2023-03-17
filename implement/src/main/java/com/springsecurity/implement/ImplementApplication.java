package com.springsecurity.implement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImplementApplication{
    public static void main(String[] args) {
        System.out.println("My Boot App - main - begin");
        SpringApplication.run(ImplementApplication.class, args);
        System.out.println("My Boot App - main - end");
    }
}
