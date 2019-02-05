package ru.devufa.debt.service.common.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.devufa.debt.entity.*;
import ru.devufa.debt.repository.currency.CurrencyRepositoryService;
import ru.devufa.debt.repository.debt.DebtRepositoryService;
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

    @Override
    public void accept(String id, boolean isAccepted) {
        Debt debt = debtRepositoryService.read(UUID.fromString(id));
        if (debt == null || debt.getStatus() == Status.WAITING_FOR_REGISTRATION) {
            throw new RuntimeException("Долго\\Займ не доступен");
        }
        Person currentPerson = securityService.getCurrentPerson();
        if (!debt.getReceiver().getId().equals(currentPerson.getId()) && !debt.getInitiator().getId().equals(currentPerson.getId())) {
            throw new RuntimeException("У вас нет прав для редактирования долга\\займа " + id);
        }
        if (debt.getStatus().equals(Status.WAITING_FOR_ACCEPTING)
                && debt.getOldStatus() != Status.WAITING_FOR_PAYMENT
                && debt.getReceiver().getId().equals(currentPerson.getId())) {
            debt.setStatus(isAccepted ? Status.WAITING_FOR_PAYMENT : Status.CLOSED);
            debtRepositoryService.update(debt);
            return;
        }

        //Если долг ожидает подтвержения и старый статус "ожидает оплаты"
        if (debt.getStatus() == Status.WAITING_FOR_ACCEPTING && debt.getOldStatus() == Status.WAITING_FOR_PAYMENT) {
            //Если тип Кредит и получатель кредитор или тип ЗАЙМ и инициатор кредитор
            // то проставить подтверждение(или вернуть на этап ожидания подтверждения)
            if ((debt.getDebtType().equals(DebtType.DEBT) && debt.getReceiver().getId().equals(currentPerson.getId()))
                    || (debt.getDebtType().equals(DebtType.LOAN) && debt.getInitiator().getId().equals(currentPerson.getId()))) {
                debt.setOldStatus(null);
                debt.setStatus(isAccepted ? Status.CLOSED : Status.WAITING_FOR_PAYMENT);
                debtRepositoryService.update(debt);
                return;

            }
        }
        throw new RuntimeException("Вы не имеете права подверждать данный долг\\заем");
    }

    @Override
    public void pay(String id) {
        Debt debt = debtRepositoryService.read(UUID.fromString(id));
        if (debt == null) {
            throw new RuntimeException("Долго\\Займ не найден");
        }
        Person currentPerson = securityService.getCurrentPerson();
        if ((debt.getStatus() == Status.WAITING_FOR_PAYMENT
                && debt.getDebtType() == DebtType.DEBT && debt.getInitiator().getId().equals(currentPerson.getId()))
                || (debt.getDebtType() == DebtType.LOAN && debt.getReceiver().getId().equals(currentPerson.getId()))) {
            debt.setOldStatus(debt.getStatus());
            debt.setStatus(Status.WAITING_FOR_ACCEPTING);
            debtRepositoryService.update(debt);
            return;
        }
        throw new RuntimeException("Ошибка");
    }


    @Override
    public Debt create(Debt entity) {
        Person initiator = securityService.getCurrentPerson();
        Person receiver = personService.findOrCreateWaitingForRegistration(entity.getReceiver().getTelephoneNumber());
        if (initiator.getId().equals(receiver.getId())) {
            throw new RuntimeException("Нельзя создавать долг для самого себя");//fixme
        }
        entity.setInitiator(initiator);
        entity.setReceiver(receiver);
        Currency currency = entity.getCurrency();
        if (currency == null || (currency.getId() == null && currency.getCurrencyName() == null)) {
            throw new RuntimeException("Валюта не указана");
        }
        currency = currencyRepositoryService.findByCurrencyName(currency.getCurrencyName());
        if (currency == null) {
            throw new RuntimeException("Данной валюты не существует");//fixme выкинуть исключение
        }
        entity.setCurrency(currency);
        if (receiver.isWaitingForPersonRegistration()) {
            entity.setStatus(Status.WAITING_FOR_REGISTRATION);
        } else {
            entity.setStatus(Status.WAITING_FOR_ACCEPTING);
        }

        return debtRepositoryService.create(entity);
    }

    /**
     * Получить список долгов текущего пользователя
     *
     * @param debtType типа долга(Заем, Кредит)
     * @return список долгов
     */
    @Override
    public List<Debt> findAll(DebtType debtType, List<Status> statuses) {
        Person currentPerson = securityService.getCurrentPerson();
        if (statuses == null || statuses.isEmpty()) {
            statuses = new ArrayList<>();
            statuses.add(Status.WAITING_FOR_ACCEPTING);
            statuses.add(Status.WAITING_FOR_PAYMENT);
        }
        if (debtType.equals(DebtType.DEBT)) {
            return debtRepositoryService.findAllDebt(currentPerson, statuses);
        } else if (debtType.equals(DebtType.LOAN)) {
            return debtRepositoryService.findAllLoan(currentPerson, statuses);
        }
        return null;
    }

}
