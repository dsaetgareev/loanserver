package ru.devufa.debt.repository.person;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import ru.devufa.debt.entity.Person;
import ru.devufa.debt.entity.Settings;
import ru.devufa.debt.repository.AbstractRepositoryTest;

import java.util.ArrayList;

@SpringBootTest(classes = {PersonRepository.class})
@EntityScan("ru.devufa.debt.entity")
public class PersonRepositoryTest extends AbstractRepositoryTest<Person> {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void findByTelephoneNumber() {
        personRepository.save(generateEntity());
        Person firstByTelephoneNumber = personRepository.findFirstByTelephoneNumber("123456");
        Assert.assertNotNull(firstByTelephoneNumber);
    }

    @Override
    protected Person generateEntity() {
        Person person = new Person();
        person.setTelephoneNumber("123456");
        person.setSettings(new ArrayList<>());
        person.getSettings().add(new Settings("Language", "ru"));
        return person;
    }
}