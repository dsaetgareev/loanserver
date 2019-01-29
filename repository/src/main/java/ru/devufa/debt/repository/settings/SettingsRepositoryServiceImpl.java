package ru.devufa.debt.repository.settings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.devufa.debt.entity.Person;
import ru.devufa.debt.entity.SettingParam;
import ru.devufa.debt.entity.Settings;
import ru.devufa.debt.repository.common.AbstractRepositoryService;
import ru.devufa.debt.repository.common.RepositoryService;

@Service
public class SettingsRepositoryServiceImpl extends AbstractRepositoryService<Settings> implements SettingsRepositoryService {

    @Autowired
    private SettingsRepository settingsRepository;

    @Override
    public Settings findByPerson(Person person) {
        return settingsRepository.findByPerson(person);
    }

    @Override
    public Settings findByPersonAndKey(Person person, SettingParam key) {
        return settingsRepository.findByPersonAndKey(person, key);
    }

    @Override
    public Settings create(Settings entity) {
        Settings settings = settingsRepository.findByPersonAndKey(entity.getPerson(), entity.getKey());
        if (settings != null) {
            settings.setValue(entity.getValue());
            return this.update(settings);
        } else {
            return super.create(entity);
        }
    }
}
