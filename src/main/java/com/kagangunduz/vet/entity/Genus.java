package com.kagangunduz.vet.entity;

public enum Genus {

    CAT("Kedi"),
    DOG("Köpek"),
    COW("İnek"),
    GOAT("Keçi"),
    SHEEP("Koyun"),
    HORSE("At");

    private final String value;

    Genus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
