package com.example.userservice.common;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
@Getter
@Setter
public class ApplicationVar {
    private ApplicationVar(){}
    private static final ApplicationVar instance = new ApplicationVar();
    private List<String> allCountry;

    public static ApplicationVar getInstance(){
        return instance;
    }
}
