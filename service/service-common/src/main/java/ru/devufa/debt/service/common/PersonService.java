package ru.devufa.debt.service.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.devufa.debt.entity.Person;
import ru.devufa.debt.repository.PersonRepository;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Transactional
    public UUID create(Person person) {
        personRepository.saveAndFlush(person);
        return person.getId();
    }
}
