package com.inshur.weather.service;

import com.inshur.weather.client.WeatherClient;
import com.inshur.weather.exception.BadInputException;
import com.inshur.weather.model.DailyWeather;
import com.inshur.weather.response.WeatherResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherServiceImpl implements WeatherService {

    private final WeatherClient weatherClient;

    @Override
    public WeatherResponse getWeatherForecast(String latitude, String longitude) {
        validate(latitude, longitude);
        List<DailyWeather> dailyWeathers = weatherClient.getCurrentWeather(latitude, longitude);
        DailyWeather dailyWeather = getResponse(dailyWeathers);
        return WeatherResponse.builder().date(Instant.ofEpochSecond(dailyWeather.getDate())
                        .atZone(ZoneId.systemDefault()).toLocalDate()).humidity(dailyWeather.getHumidity())
                .maxTemperature(dailyWeather.getTemp().getMax()).build();
    }

    private DailyWeather getResponse(List<DailyWeather> weather) {

        //Map to get the temperature and humidity
        Map<Double, List<DailyWeather>> map = new HashMap<>();

        for (DailyWeather dailyWeather : weather) {
            map.compute(dailyWeather.getTemp().getMax(), (ID, list) -> list == null ? new ArrayList<>() : list).add(dailyWeather);
        }

        //Find max temperature <key,List<DailyWeather>>
        List<DailyWeather> dailyWeatherList = map.entrySet().stream().max(Comparator.comparing(Map.Entry::getKey)).get().getValue();

        if (dailyWeatherList.size() == 1) {
            return dailyWeatherList.get(0);
        } else {
            return getIfDuplicate(dailyWeatherList);
        }
    }

    private DailyWeather getIfDuplicate(List<DailyWeather> dailyWeathers) {
        DailyWeather dailyWeather = dailyWeathers.get(0);
        int minHumidity = dailyWeather.getHumidity(); //10

        for (int i = 1; i < dailyWeathers.size(); i++) {
            DailyWeather nextDailyWeather = dailyWeathers.get(i);

            if (nextDailyWeather.getHumidity() < minHumidity) {
                minHumidity = nextDailyWeather.getHumidity();
                dailyWeather = nextDailyWeather;
            }
            if (minHumidity == nextDailyWeather.getHumidity()) {
                dailyWeather = dailyWeather.getDate() < nextDailyWeather.getDate() ? dailyWeather : nextDailyWeather;
            }
        }
        return dailyWeather;
    }

    private void validate(String lat, String lon) {
        if (lat.isBlank() || lon.isBlank()) {
            throw new BadInputException("Invalid Query Param");
        }

    }
}