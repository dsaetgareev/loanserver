package ru.devufa.debt.entity;

import ru.devufa.debt.entity.common.EntityWithId;

import javax.persistence.*;

@Entity
@Table(name = "currency_cost")
public class CrossCurrencyCost extends EntityWithId {

    /*
        Валюта в которую переводим
     */
    @OneToOne
    @JoinColumn(name = "currency_to", nullable = false)
    private Currency currencyTo;
    /*
        Валюта из которой переводим
     */
    @OneToOne
    @JoinColumn(name = "currency_from", nullable = false)
    private Currency currencyFrom;
    @Column(nullable = false)
    private Float cost;

    public Currency getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(Currency currencyTo) {
        this.currencyTo = currencyTo;
    }

    public Currency getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(Currency currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }
}
