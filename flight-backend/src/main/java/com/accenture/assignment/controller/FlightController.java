package com.accenture.assignment.controller;

import com.accenture.assignment.model.Runways;
import com.accenture.assignment.service.FlightService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("runways/")
public class FlightController {
    private FlightService flightService;

    @GetMapping("code")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, List<Runways>> getRunwaysByCode(@RequestParam String code) {
        return this.flightService.findRunwaysForEachAirportByCountryCode(code);
    }

    @GetMapping("name")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, List<Runways>> getRunwaysByName(@RequestParam String name) {
        return this.flightService.findRunwaysForEachAirportByCountryName(name);
    }

    @GetMapping("topten")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Long> getTopTenCountries() {
        return this.flightService.getTopTenCountriesByAirport();
    }



}
