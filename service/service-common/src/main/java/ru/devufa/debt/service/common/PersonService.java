package ru.devufa.debt.service.common;

import ru.devufa.debt.entity.Person;

public interface PersonService {
    Person findOrCreateNotRegistred(Person person, String code);
    Person update(Person person);
    Person findOrCreateNotRegistred(String telephoneNumber);
    void createRegistrationRequest(String telephoneNumber);
}
