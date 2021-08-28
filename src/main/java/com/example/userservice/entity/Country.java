package com.example.userservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Country extends BaseEntity {
    @Id
    private String  countryName;
    private Long population;
    private Integer ranking;
}
