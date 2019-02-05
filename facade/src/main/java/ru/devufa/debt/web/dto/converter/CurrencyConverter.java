package ru.devufa.debt.web.dto.converter;

import org.springframework.core.convert.converter.Converter;
import ru.devufa.debt.entity.Currency;
import ru.devufa.debt.web.dto.CurrencyDTO;

public class CurrencyConverter implements Converter<Currency, CurrencyDTO> {

    @Override
    public CurrencyDTO convert(Currency currency) {
        return new CurrencyDTO(currency.getCurrencyName());
    }
}
