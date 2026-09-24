package com.example.pilotbuddy.datamodel;

public class Frequency {

    private final String type;  // e.g. "Tower"
    private final String value; // e.g. "118.5"

    public Frequency(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

}
