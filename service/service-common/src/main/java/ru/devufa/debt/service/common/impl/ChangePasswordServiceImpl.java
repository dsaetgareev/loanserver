package ru.devufa.debt.service.common.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.devufa.debt.email.interfaces.EmailService;
import ru.devufa.debt.entity.Person;
import ru.devufa.debt.entity.SettingParam;
import ru.devufa.debt.entity.Settings;
import ru.devufa.debt.repository.person.PersonRepositoryService;
import ru.devufa.debt.repository.settings.SettingsRepositoryService;
import ru.devufa.debt.service.common.ChangePasswordService;

import java.util.Random;

/**
 * Сервис смены пароля
 * @author nsychev
 */
@Service
public class ChangePasswordServiceImpl implements ChangePasswordService{

    @Autowired
    private PersonRepositoryService personRepositoryService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SettingsRepositoryService settingsRepositoryService;

    @Autowired
    private EmailService emailService;

    @Override
    public void changePassword(String telephoneNumber, String uniqueCode, String newPassword) {
        Person person = personRepositoryService.findFirstByTelephoneNumber(telephoneNumber);
        if (person == null) {
            throw new RuntimeException();//todo throw exception
        }
        Settings foundSettings = settingsRepositoryService.findByPersonAndKey(person, SettingParam.CHANGE_PASS_CODE);
        if (foundSettings == null) {
            foundSettings = settingsRepositoryService.findByPersonAndKey(person, SettingParam.CREATE_PERSON_CODE);
        }
        if (foundSettings != null && foundSettings.getValue().equals(uniqueCode)) {
            person.setPassword(passwordEncoder.encode(newPassword));
            personRepositoryService.update(person);
            //settingsRepositoryService.delete(foundSettings.getId());
            return;
        }
        throw new RuntimeException();
    }

    @Override
    public String changePasswordRequest(String telephoneNumber) {
        Person person = personRepositoryService.findFirstByTelephoneNumber(telephoneNumber);
        if (person == null || person.getEmail() == null) {
            throw new RuntimeException();//todo throw exception
        }
        //Удаление старого кода если он был
        Settings existSettings = settingsRepositoryService.findByPersonAndKey(person, SettingParam.CHANGE_PASS_CODE);
        if (existSettings != null) {
            settingsRepositoryService.delete(existSettings.getId());
        }

        Random random = new Random();
        String code = String.valueOf(random.nextInt(100000 - 10000) + 10000);
        Settings newSettings = new Settings(SettingParam.CHANGE_PASS_CODE, code);
        newSettings.setPerson(person);
        settingsRepositoryService.create(newSettings);
        //todo обдумать и реализовать формирование темы и тела письма(Запросить у Алмаза + i18n)
        //emailService.send(person.getEmail(), "", "");
        return code;
    }
}
