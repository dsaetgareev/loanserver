package ru.devufa.debt.service.common.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.devufa.debt.entity.*;
import ru.devufa.debt.repository.currency.CurrencyRepositoryService;
import ru.devufa.debt.repository.debt.DebtRepositoryService;
import ru.devufa.debt.repository.person.PersonRepositoryService;
import ru.devufa.debt.service.common.DebtService;
import ru.devufa.debt.service.common.PersonService;
import ru.devufa.debt.service.security.SecurityService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DebtServiceImpl implements DebtService {

    @Autowired
    private DebtRepositoryService debtRepositoryService;
    @Autowired
    private CurrencyRepositoryService currencyRepositoryService;
    @Autowired
    private PersonService personService;
    @Autowired
    private SecurityService securityService;

    public void acceptDebt(String id, boolean isAccepted) {
        Debt debt = debtRepositoryService.read(UUID.fromString(id));
        if (debt != null) {
            Person currentPerson = securityService.getCurrentPerson();
            if (debt.getReceiver().getId().equals(currentPerson.getId())) {
                if (debt.getStatus() == Status.WAITING_FOR_ACCEPTING) {
                    debt.setStatus(isAccepted ? Status.WAITING_FOR_PAYMENT : Status.DECLINED);
                    debtRepositoryService.update(debt);
                } else if (debt.getStatus() == Status.WAITING_FOR_PAYMENT) {
                    debt.setStatus(isAccepted ? Status.CLOSED : Status.DECLINED);
                }
            }
        }
    }

    public void payDebt() {

    }


    @Override
    public Debt create(Debt entity) {
        Person initiator = securityService.getCurrentPerson();
        Person receiver = personService.create(entity.getReceiver().getTelephoneNumber());
        entity.setInitiator(initiator);
        entity.setReceiver(receiver);
        Currency currency = entity.getCurrency();
        if (currency.getId() == null && currency.getCurrencyName() != null) {
            currency = currencyRepositoryService.findByCurrencyName(currency.getCurrencyName());
            if (currency == null) {
                throw new RuntimeException();//fixme выкинуть исключение
            }
            entity.setCurrency(currency);
        }
        if (receiver.isWaitingForPersonRegistration()) {
            entity.setStatus(Status.WAITING_FOR_REGISTRATION);
        } else {
            entity.setStatus(Status.WAITING_FOR_ACCEPTING);
        }

        return debtRepositoryService.create(entity);
    }

    /**
     * Получить список долгов текущего пользователя
     * @param debtType типа долга(Заем, Кредит)
     * @return список долгов
     */
    @Override
    public List<Debt> findAll(DebtType debtType,List<Status> statuses) {
        Person currentPerson = securityService.getCurrentPerson();
        if (statuses == null || statuses.isEmpty()) {
            statuses = new ArrayList<>();
            statuses.add(Status.WAITING_FOR_ACCEPTING);
            statuses.add(Status.WAITING_FOR_PAYMENT);
        }
        return debtRepositoryService.findAllDebt(currentPerson, debtType, (Status[])statuses.toArray());
    }

}
