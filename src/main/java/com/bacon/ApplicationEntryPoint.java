package com.bacon;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@EnableConfigurationProperties
public class ApplicationEntryPoint {
    public static void main(String[] args) {
        run(ApplicationEntryPoint.class, args);
    }
}
