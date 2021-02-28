package com.accenture.assignment.repository;

import lombok.Getter;

@Getter
public class NumberOfAirportsPerCountry {

    private String isocountry;
    private Long count;

    public NumberOfAirportsPerCountry(String isocountry, Long count) {
        this.isocountry = isocountry;
        this.count = count;
    }
}
