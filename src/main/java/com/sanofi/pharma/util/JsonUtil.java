package com.sanofi.pharma.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 * @author ELLE Q
 * @since 2025-10-26
 */
@Component
public class JsonUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @PostConstruct
    public void init() {
        OBJECT_MAPPER.registerModule(new JavaTimeModule());
        OBJECT_MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public static String toJsonString(Object object) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("to json fail: " + e.getMessage(), e);
        }
    }

    public static <T> List<T> toList(String jsonString, Class<T> clazz) {
        try {
            if (jsonString == null || jsonString.trim().isEmpty()) {
                return null;
            }
            return OBJECT_MAPPER.readValue(jsonString,
                    OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("json to list fail: " + e.getMessage(), e);
        }
    }
}
