package ru.devufa.debt.repository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ru.devufa.debt.repository")
public class DebtSpringBootApplicationTest {

    public static void main(String[] args) {
        SpringApplication.run(DebtSpringBootApplicationTest.class, args);
    }
}
