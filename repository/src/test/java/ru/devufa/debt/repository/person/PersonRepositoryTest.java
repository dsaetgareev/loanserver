package ru.devufa.debt.repository.person;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import ru.devufa.debt.entity.Person;
import ru.devufa.debt.repository.AbstractRepositoryTest;

@EntityScan(basePackages = {"ru.devufa.debt.entity"})
public class PersonRepositoryTest extends AbstractRepositoryTest<Person> {

    @Override
    protected Person generateEntity() {
        return new Person();
    }
}