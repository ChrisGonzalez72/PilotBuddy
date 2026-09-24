package com.example.pilotbuddy.datamodel;

public class Runway {

    private final String designation; // e.g. "18 / 36"
    private final int lengthFeet;     // e.g. 11200
    private final String surface;     // e.g. "Asphalt"

    public Runway(String designation, int lengthFeet, String surface) {
        this.designation = designation;
        this.lengthFeet = lengthFeet;
        this.surface = surface;
    }

    public String getDesignation() {
        return designation;
    }

    public int getLengthFeet() {
        return lengthFeet;
    }

    public String getSurface() {
        return surface;
    }

}
