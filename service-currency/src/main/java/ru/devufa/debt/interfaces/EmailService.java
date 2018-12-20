package ru.devufa.debt.interfaces;

public interface EmailService {
    void send(String to, String title, String body);
}
