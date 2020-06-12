package com.banking.api.savings.models.enums;

public enum SavingsAccountStateEnum {
    OPEN("Opened savings account"),
    PENDING("This saving account was not opened in working hours");

    private String message;

    SavingsAccountStateEnum(String message) {
        this.message = message;
    }

}
