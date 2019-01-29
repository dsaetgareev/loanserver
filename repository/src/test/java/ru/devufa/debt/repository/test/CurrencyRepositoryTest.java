package ru.devufa.debt.repository.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import ru.devufa.debt.entity.Currency;
import ru.devufa.debt.repository.currency.CurrencyRepository;

public class CurrencyRepositoryTest extends AbstractRepositoryTest<Currency>{

    @Autowired
    private CurrencyRepository currencyRepository;
    @Test
    public void findByCurrencyName() {
        Currency currency = generateEntity();
        currencyRepository.saveAndFlush(currency);
        currency = currencyRepository.findByCurrencyName(currency.getCurrencyName());
        Assert.assertNotNull(currency);
    }

    @Override
    protected Currency generateEntity() {
        Currency currency = new Currency();
        currency.setCurrencyName("RUB");
        return currency;
    }

}
