package com.inshur.weather.service;

import com.inshur.weather.response.WeatherResponse;

public interface WeatherService {

    WeatherResponse getWeatherForecast(String latitude, String longitude);
}
