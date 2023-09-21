package org.hngxfreelunch.LiquidApplicationApi.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        // Configure your ObjectMapper here
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        return objectMapper;
    }

    // You can create a method to convert an object to JSON string using the custom ObjectMapper
    public String convertObjectToJsonString(Object objectToConvert, ObjectMapper objectMapper) {
        try {
            return objectMapper.writeValueAsString(objectToConvert);
        } catch (Exception e) {
            // Handle or log the exception as needed
            e.printStackTrace();
            return null; // Return null or throw a custom exception in case of an error
        }
    }
}

