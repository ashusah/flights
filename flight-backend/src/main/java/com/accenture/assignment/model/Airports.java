package com.accenture.assignment.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@Entity
public class Airports {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Column
    private String ident;

    @NotNull
    @Column
    private String type;

    @NotNull
    @Column
    private String name;

    @NotNull
    @Column
    private float latitude_deg;

    @NotNull
    @Column
    private Float longitude_deg;

    @NotNull
    @Column
    private Integer elevation_ft;

    @NotNull
    @Column
    private String continent;

    @Column
    private String isocountry;

    @NotNull
    @Column
    private String iso_region;

    @NotNull
    @Column
    private String municipality;

    @NotNull
    @Column
    private String scheduled_service;

    @NotNull
    @Column
    private String gps_code;

    @NotNull
    @Column
    private String iata_code;

    @NotNull
    @Column
    private String local_code;

    @NotNull
    @Column
    private String home_link;

    @NotNull
    @Column
    private String wikipedia_link;

    @NotNull
    @Column
    private String keywords;
}
