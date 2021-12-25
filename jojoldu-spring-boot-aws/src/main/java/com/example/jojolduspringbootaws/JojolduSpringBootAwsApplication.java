package com.example.jojolduspringbootaws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class JojolduSpringBootAwsApplication {

    public static void main(String[] args) {
        SpringApplication.run(JojolduSpringBootAwsApplication.class, args);
    }

}
