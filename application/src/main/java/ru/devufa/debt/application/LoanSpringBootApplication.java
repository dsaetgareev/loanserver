package ru.devufa.debt.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.devufa.debt.repository.config.RepositoryConfig;

@SpringBootApplication(scanBasePackages = {"ru.devufa.debt"})
public class LoanSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(new Class<?>[] {LoanSpringBootApplication.class, RepositoryConfig.class}, args);
    }
}
