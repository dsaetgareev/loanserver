package ru.devufa.debt.repository.test;


import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import ru.devufa.debt.entity.Person;
import ru.devufa.debt.entity.Settings;
import ru.devufa.debt.repository.AbstractRepositoryTest;
import ru.devufa.debt.repository.PersonRepository;

import java.util.ArrayList;
import java.util.UUID;


@EntityScan("ru.devufa.debt.entity")
public class PersonRepositoryTest extends AbstractRepositoryTest<Person> {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void findByTelephoneNumber() {
        Person person = personRepository.save(generateEntity());
        Person firstByTelephoneNumber = personRepository.findFirstByTelephoneNumber(person.getTelephoneNumber());
        Assert.assertNotNull(firstByTelephoneNumber);
    }

    @Test
    public void findByTelephoneNumberAndWaitingForRegistrationIsTrue() {
        Person person = generateEntity();
        person.setWaitingForPersonRegistration(true);
        personRepository.saveAndFlush(person);
        personRepository.findFirstByTelephoneNumberAndIsWaitingForPersonRegistrationIsTrue(person.getTelephoneNumber());
    }
    @Override
    protected Person generateEntity() {
        Person person = new Person();
        person.setTelephoneNumber(UUID.randomUUID().toString());
        person.setSettings(new ArrayList<>());
        person.getSettings().add(new Settings("Language", "ru"));
        return person;
    }
}