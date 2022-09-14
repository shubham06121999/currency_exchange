package com.example.currency.model.enums;

public enum countryCode {
    USD("USD");

    private final String value;

    countryCode(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
