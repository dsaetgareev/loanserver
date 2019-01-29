package ru.devufa.debt.entity;

import ru.devufa.debt.entity.common.EntityWithId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "currency")
public class Currency extends EntityWithId {

    /*
        Имя валюты в которую конвертируем (rub, eur..)
     */
    @Column(nullable = false, unique = true)
    private String currencyName;

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }
}
