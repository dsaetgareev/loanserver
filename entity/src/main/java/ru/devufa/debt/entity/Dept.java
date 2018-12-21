package ru.devufa.debt.entity;

import ru.devufa.debt.entity.common.EntityWithId;

import javax.persistence.*;

/**
 * Долг
 */
@Entity
@Table(name = "dept")
public class Dept extends EntityWithId{
    /*
        Кредитор
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "creditor_id", nullable = false)
    private Person creditor;
    /*
        Заемщик
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "borrower_id", nullable = false)
    private Person borrower;
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
    private Status status;

    @Override
    public void prePersist() {
        super.prePersist();
        this.status = Status.NEW;
    }

    public Person getCreditor() {
        return creditor;
    }

    public void setCreditor(Person creditor) {
        this.creditor = creditor;
    }

    public Person getBorrower() {
        return borrower;
    }

    public void setBorrower(Person borrower) {
        this.borrower = borrower;
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

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
