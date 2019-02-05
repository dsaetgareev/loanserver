package ru.devufa.debt.web.dto.converter;

import org.springframework.core.convert.converter.Converter;
import ru.devufa.debt.entity.Person;
import ru.devufa.debt.web.dto.PersonDTO;

public class PersonConverter implements Converter<Person, PersonDTO> {
    @Override
    public PersonDTO convert(Person person) {
        return new PersonDTO(person.getTelephoneNumber(), person.getPhoto(), person.getEmail(), person.getQuestion());
    }
}
