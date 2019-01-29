package ru.devufa.debt.repository.test;


import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import ru.devufa.debt.entity.Person;
import ru.devufa.debt.repository.person.PersonRepository;
import ru.devufa.debt.repository.person.PersonRepositoryService;

import java.util.UUID;


public class PersonRepositoryTest extends AbstractRepositoryTest<Person> {

    @Autowired
    private PersonRepositoryService service;

    /*@Test
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
        personRepository.findWaitingForRegistration(person.getTelephoneNumber());
    }*/
    @Override
    protected Person generateEntity() {
        Person person = new Person();
        person.setTelephoneNumber(UUID.randomUUID().toString());
        return person;
    }
}