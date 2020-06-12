package com.banking.api.savings.models.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UserMessage implements MessageEnums {

    USER_DOES_NOT_EXIST("User does nto exist", 0);

    private String message;
    private int code;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public int getCode() {
        return code;
    }

    UserMessage(String message, int code) {
        this.message = message;
        this.code = code;
    }
}
