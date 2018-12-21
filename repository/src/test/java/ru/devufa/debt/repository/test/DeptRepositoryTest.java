package ru.devufa.debt.repository.test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import ru.devufa.debt.entity.Currency;
import ru.devufa.debt.entity.Dept;
import ru.devufa.debt.entity.Person;
import ru.devufa.debt.repository.AbstractRepositoryTest;
import ru.devufa.debt.repository.CurrencyRepository;
import ru.devufa.debt.repository.PersonRepository;


@EntityScan("ru.devufa.debt.entity")
public class DeptRepositoryTest extends AbstractRepositoryTest<Dept> {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private CurrencyRepository currencyRepository;
    @Override
    protected Dept generateEntity() {
        Dept dept = new Dept();
        dept.setCreditor(getPerson("321"));
        dept.setBorrower(getPerson("123"));
        dept.setComment("Дай денег");
        dept.setCount(100);
        dept.setCurrency(getCurrency("RUB"));
        return dept;
    }

    private Person getPerson(String phone) {
        Person person = new Person();
        person.setTelephoneNumber(phone);
        return personRepository.saveAndFlush(person);
    }

    private Currency getCurrency(String name) {
        Currency currency = new Currency();
        currency.setCurrencyName(name);
        return currencyRepository.saveAndFlush(currency);
    }
}