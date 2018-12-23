package ru.devufa.debt.service.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.devufa.debt.email.interfaces.EmailService;
import ru.devufa.debt.entity.Person;
import ru.devufa.debt.entity.Settings;
import ru.devufa.debt.repository.PersonRepository;
import ru.devufa.debt.repository.SettingsRepository;

import java.util.Random;

/**
 * Сервис смены пароля
 * @author nsychev
 */
@Service
public class ChangePasswordService {

    private static final String CHANGE_PASS_CODE_SETTINGS = "changeCode"; //todo вынести в enum

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SettingsRepository settingsRepository;

    @Autowired
    private EmailService emailService;

    public void changePassword(String telephoneNumber, String uniqueCode, String newPassword) {
        Person person = personRepository.findFirstByTelephoneNumber(telephoneNumber);
        if (person == null || person.isWaitingForPersonRegistration()) {
            throw new RuntimeException();//todo throw exception
        }
        Settings foundSettings = settingsRepository.findByPersonAndKey(person, CHANGE_PASS_CODE_SETTINGS);
        if (foundSettings != null && foundSettings.getValue().equals(uniqueCode)) {
            person.setPassword(passwordEncoder.encode(newPassword));
            personRepository.saveAndFlush(person);
            settingsRepository.delete(foundSettings);
            return;
        }
        throw new RuntimeException();
    }

    public String changePasswordRequest(String telephoneNumber) {

        Person person = personRepository.findFirstByTelephoneNumber(telephoneNumber);
        if (person == null || person.getEmail() == null) {
            throw new RuntimeException();//todo throw exception
        }
        //Удаление старого кода если он был
        Settings existSettings = settingsRepository.findByPersonAndKey(person, CHANGE_PASS_CODE_SETTINGS);
        if (existSettings != null) {
            settingsRepository.delete(existSettings);
        }

        Random random = new Random();
        String code = String.valueOf(random.nextInt(100000 - 10000) + 10000);
        Settings newSettings = new Settings(CHANGE_PASS_CODE_SETTINGS, code);
        newSettings.setPerson(person);
        settingsRepository.saveAndFlush(newSettings);
        //todo обдумать и реализовать формирование темы и тела письма(Запросить у Алмаза + i18n)
        //emailService.send(person.getEmail(), "", "");
        return code;
    }
}
