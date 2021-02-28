package com.accenture.assignment.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
public class Runways {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Column
    private Integer airportref;

    @NotNull
    @Column
    private String airport_ident;

    @NotNull
    @Column
    private Integer length_ft;

    @NotNull
    @Column
    private Integer width_ft;

    @NotNull
    @Column
    private String surface;

    @NotNull
    @Column
    private Integer lighted;

    @NotNull
    @Column
    private Integer closed;

    @NotNull
    @Column
    private String le_ident;

    @NotNull
    @Column
    private Float le_latitude_deg;

    @NotNull
    @Column
    private Float le_longitude_deg;

    @NotNull
    @Column
    private Integer le_elevation_ft;

    @NotNull
    @Column
    private Float le_heading_degT;

    @NotNull
    @Column
    private Integer le_displaced_threshold_ft;

    @NotNull
    @Column
    private String he_ident;

    @NotNull
    @Column
    private Float he_latitude_deg;

    @NotNull
    @Column
    private Float he_longitude_deg;

    @NotNull
    @Column
    private Integer he_elevation_ft;

    @NotNull
    @Column
    private Float he_heading_degT;

    @NotNull
    @Column
    private Integer he_displaced_threshold_ft;
}

