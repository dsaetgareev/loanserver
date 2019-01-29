package ru.devufa.debt.repository;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

@ComponentScan(basePackages = {"ru.devufa.debt.repository", "ru.devufa.debt.entity"})
@Configuration
@EntityScan(basePackages = {"ru.devufa.debt.entity"})
@Profile("test")
public class RepositoryServiceTestConfiguration {
}
