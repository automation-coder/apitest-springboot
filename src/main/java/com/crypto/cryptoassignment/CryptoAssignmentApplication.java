package com.crypto.cryptoassignment;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class CryptoAssignmentApplication {

    @Autowired
    Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(CryptoAssignmentApplication.class, args);
    }
}
