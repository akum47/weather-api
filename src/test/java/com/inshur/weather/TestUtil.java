package com.inshur.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inshur.weather.model.DailyWeather;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class TestUtil {

    public static List<DailyWeather> getFile(String fileName) {
        ObjectMapper mapper = new ObjectMapper();

        // convert JSON array to list of books
        try {
            return Arrays.asList(mapper.readValue(Paths.get(fileName).toFile(), DailyWeather[].class));
        } catch (IOException exception) {
            exception.getMessage();
        }
        return null;
    }
}
