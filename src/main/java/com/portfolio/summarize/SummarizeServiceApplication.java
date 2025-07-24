package com.portfolio.summarize;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SummarizeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SummarizeServiceApplication.class, args);
    }
} 