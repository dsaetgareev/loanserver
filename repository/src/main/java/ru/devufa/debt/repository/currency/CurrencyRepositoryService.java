package ru.devufa.debt.repository.currency;

import ru.devufa.debt.entity.Currency;
import ru.devufa.debt.entity.Settings;
import ru.devufa.debt.repository.common.RepositoryService;

public interface CurrencyRepositoryService extends RepositoryService<Currency> {

    Currency findByCurrencyName(String name);
}
