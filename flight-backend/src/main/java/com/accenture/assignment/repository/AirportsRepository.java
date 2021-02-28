package com.accenture.assignment.repository;

import com.accenture.assignment.model.Airports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AirportsRepository extends JpaRepository<Airports, Integer> {
    List<Airports> findTop1000ByIsocountry(String isoCode);

    @Query("select new com.accenture.assignment.repository.NumberOfAirportsPerCountry(a.isocountry, count (a))" +
            " FROM Airports a GROUP BY a.isocountry ORDER BY COUNT(a) DESC ")
    List<NumberOfAirportsPerCountry>getAllCountryGroupedByAirportInDescOrder();
}

