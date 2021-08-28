package com.example.userservice.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtils {
    private JacksonUtils(){
        throw new RuntimeException("Unsupported");
    }
    public static <T> T convertObject(Object object, Class<T> t){
        ObjectMapper objectMapper = new ObjectMapper();
        T result  = objectMapper.convertValue(object, t);
        return result;
    }
}
