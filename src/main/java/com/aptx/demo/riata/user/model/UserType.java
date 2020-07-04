package com.aptx.demo.riata.user.model;

public enum UserType {
    REGULAR("REGULAR"), GITHUB("GITHUB"), GOOGLE("GOOGLE");

    public String value;

    UserType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
