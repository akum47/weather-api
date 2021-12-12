package com.inshur.weather.client;

import com.inshur.weather.model.DailyWeather;

import java.util.List;

public interface WeatherClient {

    List<DailyWeather> getCurrentWeather(String latitude, String longitude);

}
