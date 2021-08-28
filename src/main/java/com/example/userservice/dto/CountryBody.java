package com.example.userservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryBody {
    @JsonProperty(value = "country_name")
    private String  countryName;
    private Long population;
    private Integer ranking;
}
