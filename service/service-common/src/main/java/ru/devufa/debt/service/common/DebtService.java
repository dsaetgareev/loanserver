package ru.devufa.debt.service.common;

import ru.devufa.debt.entity.Debt;
import ru.devufa.debt.entity.DebtType;
import ru.devufa.debt.entity.Status;

import java.util.List;

public interface DebtService {
    Debt create(Debt debt);
    List<Debt> findAll(DebtType debtType, List<Status> statuses);
    void accept(String id, boolean isAccepted);
    void pay(String id);
}
