package ru.devufa.debt.repository.person;

import ru.devufa.debt.entity.Person;
import ru.devufa.debt.repository.common.RepositoryService;

public interface PersonRepositoryService extends RepositoryService<Person> {

    Person findFirstByTelephoneNumber(String telephoneNumber);
    Person findWaitingForRegistration(String telephoneNumber);
}
