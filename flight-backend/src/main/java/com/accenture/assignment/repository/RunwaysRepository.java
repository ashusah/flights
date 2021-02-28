package com.accenture.assignment.repository;

import com.accenture.assignment.model.Runways;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RunwaysRepository extends JpaRepository<Runways, Integer> {

    List<Runways> findTop1000ByAirportrefIn(List<Integer> airportCode);
}

