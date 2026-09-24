package com.example.pilotbuddy.datamodel;

import java.util.List;

public class Airport {

    private final String identifier; // ICAO/FAA, e.g. "KATL"
    private final String name;       // e.g. "Hartsfield–Jackson Atlanta Intl"
    private final List<Runway> runways;
    private final List<Frequency> frequencies;

    public Airport(String identifier, String name,
                   List<Runway> runways, List<Frequency> frequencies) {
        this.identifier = identifier;
        this.name = name;
        this.runways = runways;
        this.frequencies = frequencies;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public List<Runway> getRunways() {
        return runways;
    }

    public List<Frequency> getFrequencies() {
        return frequencies;
    }

}
