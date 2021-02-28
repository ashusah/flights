package com.accenture.assignment.service;

import com.accenture.assignment.exception.EntityNotFoundException;
import com.accenture.assignment.model.Airports;
import com.accenture.assignment.model.Countries;
import com.accenture.assignment.model.Runways;
import com.accenture.assignment.repository.AirportsRepository;
import com.accenture.assignment.repository.CountriesRepository;
import com.accenture.assignment.repository.NumberOfAirportsPerCountry;
import com.accenture.assignment.repository.RunwaysRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class FlightService {
    private final CountriesRepository countriesRepository;
    private final RunwaysRepository runwaysRepository;
    private final AirportsRepository airportsRepository;


    public Map<String, List<Runways>> findRunwaysForEachAirportByCountryCode(String countryCode) {

        final Countries country = countriesRepository.findByCode(countryCode);
        if (country == null) {
            throw new EntityNotFoundException("Country not found");

        }
        return findAllAirportsByCountry(country);
    }

    public Map<String, List<Runways>> findRunwaysForEachAirportByCountryName(String countryName) {

        final Countries country = countriesRepository.findByName(countryName);
        if (country == null) {
            throw new EntityNotFoundException("Country not found");

        }
        return findAllAirportsByCountry(country);
    }

    public Map<String, Long> getTopTenCountriesByAirport() {
        Map<String, Long> countryCountMap = new HashMap<>();

        final List<NumberOfAirportsPerCountry> topTenCountryByAirportCount = this.airportsRepository
                .getAllCountryGroupedByAirportInDescOrder().stream().limit(10).collect(Collectors.toList());


        final List<String> topTenCountryIsoCodes = topTenCountryByAirportCount.stream()
                .map(NumberOfAirportsPerCountry::getIsocountry).collect(Collectors.toList());

        final List<Countries> topTenCountryNames = this.countriesRepository.findAllByCodeIn(topTenCountryIsoCodes);

        for (NumberOfAirportsPerCountry airportsPerCountry : topTenCountryByAirportCount) {
            final Countries country = topTenCountryNames.stream()
                    .filter(x -> x.getCode().equals(airportsPerCountry.getIsocountry()))
                    .findFirst().get();
            countryCountMap.put(country.getName(), airportsPerCountry.getCount());
        }

        Map<String, Long> sortedMap = countryCountMap.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        return sortedMap;
    }

    private Map<String, List<Runways>> findAllAirportsByCountry(Countries country) {

        final List<Airports> airports = airportsRepository.findTop1000ByIsocountry(country.getCode());

        final List<Integer> airportIds = airports.stream().map(Airports::getId).collect(Collectors.toList());

        final List<Runways> runwaysForAllAirports = runwaysRepository.findTop1000ByAirportrefIn(airportIds);

        Map<String, List<Runways>> airportRunwayMap = new HashMap<>();

        for (Airports airport : airports) {
            List<Runways> listOfRunways = runwaysForAllAirports.stream()
                    .filter(x -> x.getAirportref().equals(airport.getId()))
                    .collect(Collectors.toList());

            airportRunwayMap.put(airport.getName(), listOfRunways);
        }

        return airportRunwayMap;

    }

}
