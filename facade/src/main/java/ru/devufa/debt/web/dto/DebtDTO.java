package ru.devufa.debt.web.dto;

public class DebtDTO {

    private String id;
    private String creditorNumber;
    private String borrowerNumber;
    private double count;
    private String currencyCode;
    private String comment;
    private String status;
    private String type;

    public DebtDTO(String id, double count, String currencyCode, String comment, String status) {
        this.id = id;
        this.count = count;
        this.currencyCode = currencyCode;
        this.comment = comment;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreditorNumber() {
        return creditorNumber;
    }

    public void setCreditorNumber(String creditorNumber) {
        this.creditorNumber = creditorNumber;
    }

    public String getBorrowerNumber() {
        return borrowerNumber;
    }

    public void setBorrowerNumber(String borrowerNumber) {
        this.borrowerNumber = borrowerNumber;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
