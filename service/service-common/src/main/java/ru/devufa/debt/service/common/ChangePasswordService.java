package ru.devufa.debt.service.common;

public interface ChangePasswordService {
    void changePassword(String telephoneNumber, String uniqueCode, String newPassword);
    String changePasswordRequest(String telephoneNumber);
}
