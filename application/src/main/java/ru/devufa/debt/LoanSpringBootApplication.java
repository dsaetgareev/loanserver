package ru.devufa.debt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"ru.devufa.debt"})
public class LoanSpringBootApplication {

    public static void main(String[] args) {
            SpringApplication.run(LoanSpringBootApplication.class, args);
    }
}
