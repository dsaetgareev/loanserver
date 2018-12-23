package ru.devufa.debt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.devufa.debt.entity.Person;
import ru.devufa.debt.entity.Settings;

import java.util.UUID;

public interface SettingsRepository extends JpaRepository<Settings, UUID> {

    Settings findByPerson(Person person);
    Settings findByPersonAndKey(Person person, String key);
}
