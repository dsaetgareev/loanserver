package ru.devufa.debt.web.dto.converter;

import org.springframework.core.convert.converter.Converter;
import ru.devufa.debt.entity.Person;
import ru.devufa.debt.web.dto.PersonDTO;

public class ToPersonConverter implements Converter<PersonDTO, Person> {

    @Override
    public Person convert(PersonDTO personDTO) {
        Person person = new Person();
        person.setTelephoneNumber(personDTO.getNumber());
        person.setEmail(personDTO.getEmail());
        person.setQuestion(personDTO.getQuestion());
        person.setPassword(personDTO.getPassword());
        person.setPhoto(personDTO.getPhoto());
        return person;
    }
}
