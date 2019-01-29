package ru.devufa.debt.repository.currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.devufa.debt.entity.Currency;
import ru.devufa.debt.entity.Settings;
import ru.devufa.debt.repository.common.AbstractRepositoryService;
import ru.devufa.debt.repository.common.RepositoryService;

@Service
public class CurrencyRepositoryServiceImpl extends AbstractRepositoryService<Currency> implements CurrencyRepositoryService {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public Currency findByCurrencyName(String name) {
        return currencyRepository.findByCurrencyName(name);
    }
}
