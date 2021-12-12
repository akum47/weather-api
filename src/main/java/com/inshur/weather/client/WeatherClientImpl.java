package com.inshur.weather.client;

import com.inshur.weather.config.WeatherConfig;
import com.inshur.weather.exception.WeatherClientException;
import com.inshur.weather.model.DailyWeather;
import com.inshur.weather.model.Weather;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherClientImpl implements WeatherClient {

    private static final String WEATHER_URL = "/onecall?lat={lat}&lon={lon}&exclude=current,hourly,minutely,alerts&appid={apiKey}";

    private final WeatherConfig weatherConfig;

    private WebClient webClient;

    public List<DailyWeather> getCurrentWeather(String latitude, String longitude) {

        webClient = WebClient.create(weatherConfig.getUrl());

        try {
            Mono<Weather> response = webClient.get()
                    .uri(WEATHER_URL, latitude, longitude, weatherConfig.getKey())
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(Weather.class)
                    .log();
            Weather weather = response.block();
            return weather.getDailyWeathers();
        } catch (Exception ex) {
            throw new WeatherClientException(ex.getMessage());
        }
    }
}
