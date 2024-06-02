package com.graphqljava.react.challenge.enums;

public enum SortBy {
    COMPANY("company"),
    FIRSTNAME("firstName"),
    LASTNAME("lastName");

    private final String fieldName;

    SortBy(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
