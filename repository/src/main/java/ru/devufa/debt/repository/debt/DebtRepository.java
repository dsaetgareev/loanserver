package ru.devufa.debt.repository.debt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.devufa.debt.entity.Debt;
import ru.devufa.debt.entity.DebtType;
import ru.devufa.debt.entity.Person;
import ru.devufa.debt.entity.Status;

import java.util.List;
import java.util.UUID;

@Repository
public interface DebtRepository extends JpaRepository<Debt, UUID> {

    @Query("select debt from Debt as debt where debt.debtType=:type and (debt.initiator = :person or debt.receiver = :person)")
    List<Debt> findAllDebt(@Param("person") Person person, @Param("type") DebtType debtType);
    @Query("select debt from Debt as debt where debt.debtType=:debtType and debt.status in (:statuses) and (debt.initiator = :person or debt.receiver = :person)")
    List<Debt> findAllDebt(@Param("person") Person person, @Param("debtType") DebtType type, @Param("statuses") Status ...statuses);

    //Все долги
    @Query("select debt from Debt as debt where debt.status in (:statuses) and ((debt.debtType='DEBT' and debt.initiator=:person) or (debt.debtType='LOAN' and debt.receiver=:person))")
    List<Debt> findAllDebt(@Param("person") Person person, @Param("statuses") List<Status> statuses);
    @Query("select debt from Debt as debt where debt.status in (:statuses) and ((debt.debtType='LOAN' and debt.initiator=:person) or (debt.debtType='DEBT' and debt.receiver=:person))")
    List<Debt> findAllLoan(@Param("person") Person person, @Param("statuses") List<Status> statuses);
    /**
     * Сменить статус на ожидает подтверждение где статус равен ожидает регистрации и инициатор или получатель = person
     * @param person
     */
    @Modifying(clearAutomatically = true)
    @Query("update Debt debt set debt.status='WAITING_FOR_ACCEPTING' where debt.status='WAITING_FOR_REGISTRATION' and debt.receiver=:person")
    void wakeUpDebt(@Param("person") Person person);
}
