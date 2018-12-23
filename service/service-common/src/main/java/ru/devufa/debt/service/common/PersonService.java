package ru.devufa.debt.service.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.devufa.debt.entity.Person;
import ru.devufa.debt.repository.PersonRepository;

/**
 * Сервис работы с пользователем
 * @author nsychev
 */
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Регистрация пользователя
     *
     * @param person данные пользователя
     * @return созданный пользователь
     */
    public Person create(Person person) {
        Person waitForRegistrationPerson = personRepository.findFirstByTelephoneNumberAndIsWaitingForPersonRegistrationIsTrue(person.getTelephoneNumber());
        if (waitForRegistrationPerson != null) {
            //todo оживить все запросы которые были созданы к этому пользователю
        }
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        return personRepository.saveAndFlush(person);
    }

    /**
     * Создание пользователя ожидающего регистрацию
     *
     * @param telephoneNumber номер телефона пользователя
     * @return пользовтеля ожидающего регистрацию
     */
    public Person create(String telephoneNumber) {
        Person person = new Person();
        person.setTelephoneNumber(telephoneNumber);
        person.setWaitingForPersonRegistration(true);
        return personRepository.saveAndFlush(person);
    }


}
