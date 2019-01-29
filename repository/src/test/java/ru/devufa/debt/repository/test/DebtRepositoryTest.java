package ru.devufa.debt.repository.test;

import org.springframework.beans.factory.annotation.Autowired;
import ru.devufa.debt.entity.*;
import ru.devufa.debt.repository.currency.CurrencyRepository;
import ru.devufa.debt.repository.debt.DebtRepository;
import ru.devufa.debt.repository.person.PersonRepository;

public class DebtRepositoryTest extends AbstractRepositoryTest<Debt> {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private CurrencyRepository currencyRepository;
    @Autowired
    private DebtRepository debtRepository;

    /*@Test
    public void getListByCreditor() {
        Debt debt = debtRepository.saveAndFlush(generateEntity());
        debtRepository.saveAndFlush(generateEntity());
        debtRepository.saveAndFlush(generateEntity());
        List<Debt> all = debtRepository.findAllDebt(debt.getInitiator(), DebtType.LOAN, Status.NEW);
        Assert.assertEquals(all.size(), 3);

        List<Debt> allByBorrower = debtRepository.findAllDebt(debt.getReceiver(),DebtType.LOAN, Status.NEW);
        Assert.assertEquals(allByBorrower.size(), 3);
    }
    @Test
    public void wakeUpDebts() {
        Debt debt = generateEntity();
        Person person = new Person();
        person.setTelephoneNumber("123456789");
        person.setWaitingForPersonRegistration(true);
        personRepository.saveAndFlush(person);
        debt.setReceiver(person);
        debtRepository.saveAndFlush(debt);
        debt.setStatus(Status.WAITING_FOR_REGISTRATION);
        debtRepository.saveAndFlush(debt);
        List<Debt> debts = debtRepository.findAllDebt(person,DebtType.LOAN, Status.WAITING_FOR_REGISTRATION);
        Assert.assertEquals(1, debts.size());
        debtRepository.wakeUpDebt(person);
        List<Debt> allByCreditor = debtRepository.findAllDebt(debt.getReceiver(), DebtType.LOAN, Status.WAITING_FOR_ACCEPTING);
        Assert.assertEquals(1, allByCreditor.size());
        Assert.assertEquals(Status.WAITING_FOR_ACCEPTING, allByCreditor.get(0).getStatus());
    }*/
    @Override
    protected Debt generateEntity() {
        Debt debt = new Debt();
        debt.setReceiver(getPerson("321"));
        debt.setInitiator(getPerson("123"));
        debt.setComment("Дай денег");
        debt.setCount(100);
        debt.setCurrency(getCurrency("RUB"));
        debt.setDebtType(DebtType.LOAN);
        return debt;
    }

    private Person getPerson(String phone) {
        Person person = personRepository.findFirstByTelephoneNumber(phone);
        if (person == null) {
            person = new Person();
            person.setTelephoneNumber(phone);
        }

        return personRepository.saveAndFlush(person);
    }

    private Currency getCurrency(String name) {
        Currency currency = currencyRepository.findByCurrencyName(name);
        if (currency == null) {
            currency = new Currency();
            currency.setCurrencyName(name);
        }
        return currencyRepository.saveAndFlush(currency);
    }
}