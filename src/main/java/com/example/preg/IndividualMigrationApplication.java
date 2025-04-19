package com.example.preg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class IndividualMigrationApplication {
    public static void main(String[] args) {
        SpringApplication.run(IndividualMigrationApplication.class, args);
    }
}