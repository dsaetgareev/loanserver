package ru.devufa.debt.repository.person;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import ru.devufa.debt.entity.Person;
import ru.devufa.debt.repository.AbstractRepositoryTest;

@SpringBootTest(classes = {PersonRepository.class})
@EntityScan("ru.devufa.debt.entity")
public class PersonRepositoryTest extends AbstractRepositoryTest<Person> {

    @Override
    protected Person generateEntity() {
        return new Person();
    }
}