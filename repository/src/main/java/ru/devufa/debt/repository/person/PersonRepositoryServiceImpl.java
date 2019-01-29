package ru.devufa.debt.repository.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.devufa.debt.entity.Person;
import ru.devufa.debt.repository.common.AbstractRepositoryService;

@Service
public class PersonRepositoryServiceImpl extends AbstractRepositoryService<Person> implements PersonRepositoryService{

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person findFirstByTelephoneNumber(String telephoneNumber) {
        return personRepository.findFirstByTelephoneNumber(telephoneNumber);
    }

    @Override
    public Person findWaitingForRegistration(String telephoneNumber) {
        return personRepository.findFirstByTelephoneNumberAndIsWaitingForPersonRegistrationIsTrue(telephoneNumber);
    }
}
