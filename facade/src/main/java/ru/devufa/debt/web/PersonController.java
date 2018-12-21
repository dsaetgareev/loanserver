package ru.devufa.debt.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.devufa.debt.entity.Person;
import ru.devufa.debt.service.common.PersonService;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping(path = "/register")
    public Person register(@RequestBody Person person) {
        return personService.create(person);
    }
}
