package ru.devufa.debt.repository.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import ru.devufa.debt.entity.Person;
import ru.devufa.debt.entity.Settings;
import ru.devufa.debt.repository.AbstractRepositoryTest;
import ru.devufa.debt.repository.PersonRepository;
import ru.devufa.debt.repository.SettingsRepository;

import java.util.Optional;

@EntityScan("ru.devufa.debt.entity")
public class SettingsRepositoryTest extends AbstractRepositoryTest<Settings>{

    @Autowired
    private SettingsRepository settingsRepository;
    @Autowired
    private PersonRepository personRepository;
    @Test
    public void findByPerson() {
        Settings settings = generateEntity();
        Person person = generatePerson();
        settings.setPerson(person);
        settingsRepository.saveAndFlush(settings);
        Settings settings1 = settingsRepository.findByPerson(person);
        Assert.assertNotNull(settings1);
    }
    @Test
    public void findByPersonAndKey() {
        Settings settings = generateEntity();
        Person person = generatePerson();
        settings.setPerson(person);
        settingsRepository.saveAndFlush(settings);
        Settings settings1 = settingsRepository.findByPersonAndKey(person, "123");
        Assert.assertNotNull(settings1);
    }
    @Override
    protected Settings generateEntity() {
        Settings settings = new Settings();
        settings.setKey("123");
        settings.setValue("123");
        return settings;
    }

    private Person generatePerson() {
        Person person = new Person();
        person.setTelephoneNumber("111");
        return personRepository.saveAndFlush(person);
    }
}
