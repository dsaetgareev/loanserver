package ru.devufa.debt.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.devufa.debt.entity.common.EntityWithId;

import javax.persistence.*;

/**
 * Долг
 */
@Entity
@Table(name = "dept")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Debt extends EntityWithId{
    /*
        Кредитор
     */
    @ManyToOne
    @JoinColumn(name = "initiator_id", nullable = false)
    private Person initiator;
    /*
        Заемщик
     */
    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private Person receiver;
    /*
        Сумма займа
     */
    @Column(nullable = false)
    private double count;
    /*
        Валюта
     */
    @ManyToOne
    @JoinColumn(name = "currency_id", nullable = false)
    private Currency currency;
    /*
        Комментарий к заему
     */
    @Column(nullable = false)
    private String comment;
    /*
        Статус заема
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    @Enumerated(EnumType.STRING)
    private Status oldStatus;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DebtType debtType;

    @Override
    public void prePersist() {
        super.prePersist();
        if (this.status == null) {
            this.status = Status.NEW;
        }
    }

    public Person getInitiator() {
        return initiator;
    }

    public void setInitiator(Person initiator) {
        this.initiator = initiator;
    }

    public Person getReceiver() {
        return receiver;
    }

    public void setReceiver(Person receiver) {
        this.receiver = receiver;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Currency getCurrency() {
        return currency;
    }

    public DebtType getDebtType() {
        return debtType;
    }

    public void setDebtType(DebtType debtType) {
        this.debtType = debtType;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getOldStatus() {
        return oldStatus;
    }

    public void setOldStatus(Status oldStatus) {
        this.oldStatus = oldStatus;
    }
}
