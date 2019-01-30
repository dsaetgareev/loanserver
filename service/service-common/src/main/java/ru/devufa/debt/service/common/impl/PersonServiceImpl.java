package ru.devufa.debt.service.common.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.devufa.debt.entity.Person;
import ru.devufa.debt.entity.SettingParam;
import ru.devufa.debt.entity.Settings;
import ru.devufa.debt.repository.debt.DebtRepositoryService;
import ru.devufa.debt.repository.person.PersonRepositoryService;
import ru.devufa.debt.repository.settings.SettingsRepositoryService;
import ru.devufa.debt.service.common.PersonService;

import java.util.Random;

/**
 * Сервис работы с пользователем
 * @author nsychev
 */
@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private DebtRepositoryService debtRepositoryService;
    @Autowired
    private PersonRepositoryService personRepositoryService;
    @Autowired
    private SettingsRepositoryService settingsRepositoryService;
    /**
     * Регистрация пользователя
     *
     * @param person данные пользователя
     * @return созданный пользователь
     */
    @Override
    public Person create(Person person,String code) {
        Person waitForRegistrationPerson = personRepositoryService.findWaitingForRegistration(person.getTelephoneNumber());
        //Если найдена персона ожидающая регистрацию то переносим все данные в нее и пробуждаем всем долги ожидющие регистрации этой персоны
        Settings settings = settingsRepositoryService.findByPersonAndKey(waitForRegistrationPerson, SettingParam.CREATE_PERSON_CODE);
        if (waitForRegistrationPerson == null || settings == null) {
            throw new RuntimeException("Не был отправлен запрос на код регистрации");
        }
        if (settings.getValue().equals(code)) {
            person.setId(waitForRegistrationPerson.getId());
            person.setPassword(person.getPassword());
            person.setPassword(passwordEncoder.encode(person.getPassword()));
            personRepositoryService.update(person);
            settingsRepositoryService.delete(settings.getId());
            debtRepositoryService.wakeUpDebt(person);//Возможно сделать асинхронным
            return person;
        }
        throw new RuntimeException("Не правильный код регистрации");
    }

    @Override
    public Person update(Person person) {
        return null;
    }

    /**
     * Создание пользователя ожидающего регистрацию
     *
     * @param telephoneNumber номер телефона пользователя
     * @return пользовтеля ожидающего регистрацию
     */
    @Override
    public Person create(String telephoneNumber) {
        Person existPerson = personRepositoryService.findFirstByTelephoneNumber(telephoneNumber);
        if (existPerson == null) {
            Person person = new Person();
            person.setTelephoneNumber(telephoneNumber);
            person.setWaitingForPersonRegistration(true);
            return personRepositoryService.create(person);
        }
        return existPerson;
    }

    @Override
    public void createRegistrationRequest(String telephoneNumber) {
        Person person = create(telephoneNumber);
        if (person.isWaitingForPersonRegistration()) {
            Random random = new Random();
            String code = String.valueOf(random.nextInt(100000 - 10000) + 10000);
            settingsRepositoryService.create(new Settings(SettingParam.CREATE_PERSON_CODE, code, person));
            //todo отправить смс
        }
    }
}
