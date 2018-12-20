package ru.devufa.debt.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.devufa.debt.entity.Person;
import ru.devufa.debt.repository.PersonRepository;
import ru.devufa.debt.service.common.PersonService;

import java.util.UUID;

@RestController
public class TestController {

    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository personRepository;
    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public UUID test() {
        Person person = new Person();
        person.setTelephoneNumber("89373078701");
        person.setEmail("stem25@icloud.com");
        person.setQuestion("???");
        person.setAnswer("123");
        return personService.create(person);
    }
}
