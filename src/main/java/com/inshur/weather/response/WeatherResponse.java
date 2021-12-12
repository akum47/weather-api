package com.inshur.weather.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class WeatherResponse {

    private LocalDate date;
    private double maxTemperature;
    private int humidity;

}
