package com.example.userservice.dto;

import com.example.userservice.entity.Country;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryDto {
    private Boolean ok;
    private CountryBody body;
}
