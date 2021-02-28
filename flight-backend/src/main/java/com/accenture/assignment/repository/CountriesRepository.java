package com.accenture.assignment.repository;

import com.accenture.assignment.model.Countries;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountriesRepository extends JpaRepository<Countries, Integer> {

    Countries findByCode(String countryCode);

    Countries findByName(String name);

    List<Countries>  findAllByCodeIn(List<String> countryCode);
}
