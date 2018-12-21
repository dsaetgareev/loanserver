package ru.devufa.debt.service.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.devufa.debt.entity.Person;
import ru.devufa.debt.entity.Settings;
import ru.devufa.debt.repository.PersonRepository;

import java.util.Map;
import java.util.Random;

/**
 * Сервис работы с пользователем
 */
@Service
public class PersonService {
    private static final String CHANGE_PASS_CODE_SETTINGS = "changeCode"; //todo вынести в enum
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

    public void changePassword(String telephoneNumber, String uniqueCode, String newPassword) {
        Person person = personRepository.findFirstByTelephoneNumber(telephoneNumber);
        if (person == null || person.isWaitingForPersonRegistration()) {
            throw new RuntimeException();//todo throw exception
        }
        if (!person.getSettings().isEmpty()) {
            boolean isEqualCode = false;
            //todo вынести поиск кода сброса на слой репозиториев
            for (Settings settings : person.getSettings()) {
                if (settings.getKey().equals(CHANGE_PASS_CODE_SETTINGS) && settings.getValue().equals(uniqueCode)) {
                    isEqualCode = true;
                    break;
                }
            }
            if (isEqualCode) {
                person.setPassword(passwordEncoder.encode(newPassword));
                //todo очистить код сброса
                personRepository.saveAndFlush(person);
            }
            return;
        }
        throw new RuntimeException();
    }

    public String changePasswordRequest(String telephoneNumber) {

        Person person = personRepository.findFirstByTelephoneNumber(telephoneNumber);
        if (person == null) {
            throw new RuntimeException();//todo throw exception
        }
        //Удаление старого кода если он был
        //todo перенести на слой репозитриев
        if (!person.getSettings().isEmpty()) {
            int i = -1;
            for (Settings setting : person.getSettings()) {
                if (setting.getKey().equals(CHANGE_PASS_CODE_SETTINGS)) {
                    break;
                }
                i++;
            }
            if (i != -1) person.getSettings().remove(i + 1);
        }
        Random random = new Random();
        String code = String.valueOf(random.nextInt(100000 - 10000) + 10000);
        person.getSettings().add(new Settings(CHANGE_PASS_CODE_SETTINGS, code));
        personRepository.saveAndFlush(person);
        //todo sendemail
        return code;
    }
}
