package com.example.userservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CountryNameBody {
    private List<String> countries;
}
