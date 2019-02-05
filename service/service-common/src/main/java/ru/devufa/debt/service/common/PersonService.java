package ru.devufa.debt.service.common;

import ru.devufa.debt.entity.Person;

public interface PersonService {
    Person create(Person person, String code);
    Person update(Person person);
    Person findOrCreateWaitingForRegistration(String telephoneNumber);
    void createRegistrationRequest(String telephoneNumber);
    boolean verifyCode(String telephoneNumber, String code);
}
