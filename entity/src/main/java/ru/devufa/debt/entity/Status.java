package ru.devufa.debt.entity;

public enum Status {
    //Новый
    NEW,
    //Ожидат регистрации
    WAITING_FOR_REGISTRATION,
    //Ожидает подтверждения
    WAITING_FOR_ACCEPTING,
    //Отказано
    DECLINED,
    //Подтвержден
    ACCEPTED,
    //Закрыт
    CLOSED
}
