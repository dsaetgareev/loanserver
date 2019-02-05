package ru.devufa.debt.web.dto;

public class CurrencyDTO {

    private String code;

    public CurrencyDTO() {
    }

    public CurrencyDTO(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
