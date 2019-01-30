package ru.devufa.debt.repository.debt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.devufa.debt.entity.Debt;
import ru.devufa.debt.entity.DebtType;
import ru.devufa.debt.entity.Person;
import ru.devufa.debt.entity.Status;
import ru.devufa.debt.repository.common.AbstractRepositoryService;

import java.util.List;

@Service
public class DebtRepositoryServiceImpl extends AbstractRepositoryService<Debt> implements DebtRepositoryService {

    @Autowired
    private DebtRepository debtRepository;

    @Override
    public List<Debt> findAllDebt(Person person, DebtType debtType) {
        return debtRepository.findAllDebt(person, debtType);
    }

    @Override
    public List<Debt> findAllDebt(Person person, DebtType type, Status... statuses) {
        return debtRepository.findAllDebt(person, type, statuses);
    }

    @Override
    public List<Debt> findAllDebt(Person person, List<Status> statuses) {
        return debtRepository.findAllDebt(person, statuses);
    }

    @Override
    public List<Debt> findAllLoan(Person person, List<Status> statuses) {
        return debtRepository.findAllLoan(person, statuses);
    }

    @Override
    @Transactional
    public void wakeUpDebt(Person person) {
        debtRepository.wakeUpDebt(person);
    }
}
