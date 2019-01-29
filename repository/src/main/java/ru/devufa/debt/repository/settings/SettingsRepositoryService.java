package ru.devufa.debt.repository.settings;

import ru.devufa.debt.entity.Person;
import ru.devufa.debt.entity.SettingParam;
import ru.devufa.debt.entity.Settings;
import ru.devufa.debt.repository.common.RepositoryService;

public interface SettingsRepositoryService extends RepositoryService<Settings> {

    Settings findByPerson(Person person);
    Settings findByPersonAndKey(Person person, SettingParam key);
}
