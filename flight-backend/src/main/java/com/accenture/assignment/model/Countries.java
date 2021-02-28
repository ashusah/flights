package com.accenture.assignment.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@Entity
public class Countries {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Column
    private String code;

    @NotNull
    @Column
    private String name;

    @NotNull
    @Column
    private String continent;

    @NotNull
    @Column
    private String wikipedia_link;

    @NotNull
    @Column
    private String keywords;
}
