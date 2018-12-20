package ru.devufa.debt.repository.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.devufa.debt.entity.Person;

import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {

    Person findFirstByTelephoneNumber(String telephoneNumber);
}
