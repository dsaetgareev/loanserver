package ru.devufa.debt.repository.debt;

import ru.devufa.debt.entity.Debt;
import ru.devufa.debt.entity.DebtType;
import ru.devufa.debt.entity.Person;
import ru.devufa.debt.entity.Status;
import ru.devufa.debt.repository.common.RepositoryService;

import java.util.List;

public interface DebtRepositoryService extends RepositoryService<Debt> {

    List<Debt> findAllDebt(Person person, DebtType debtType);

    List<Debt> findAllDebt(Person person, DebtType type, Status...statuses);

    List<Debt> findAllDebt(Person person, List<Status> statuses);

    List<Debt> findAllLoan(Person person, List<Status> statuses);
    /**
     * Сменить статус на ожидает подтверждение где статус равен ожидает регистрации и инициатор или получатель = person
     * @param person
     */
    void wakeUpDebt(Person person);
}
