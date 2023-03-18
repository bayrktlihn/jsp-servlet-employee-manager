package io.bayrktlihn.employeecrudapp.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringReader;

public class JSON {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    public static <T> String stringify(T t) {
        try {
            return OBJECT_MAPPER.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T parse(String json, Class<T> clazz) {
        StringReader sr = new StringReader(json);
        try {
            return OBJECT_MAPPER.readValue(sr, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
