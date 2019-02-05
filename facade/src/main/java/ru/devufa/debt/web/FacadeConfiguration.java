package ru.devufa.debt.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import ru.devufa.debt.web.dto.converter.CurrencyConverter;
import ru.devufa.debt.web.dto.converter.DebtConverter;
import ru.devufa.debt.web.dto.converter.PersonConverter;
import ru.devufa.debt.web.dto.converter.ToPersonConverter;

@Configuration
public class FacadeConfiguration {

    @Bean(name = "DTOConversionService")
    public ConversionService conversionService() {
        DefaultConversionService defaultConversionService = new DefaultConversionService();
        defaultConversionService.addConverter(new CurrencyConverter());
        defaultConversionService.addConverter(new DebtConverter());
        defaultConversionService.addConverter(new PersonConverter());
        defaultConversionService.addConverter(new ToPersonConverter());
        return defaultConversionService;
    }
}
