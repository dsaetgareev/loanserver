package ru.devufa.debt.email.interfaces;

public interface EmailService {
    void send(String to, String title, String body);
}
