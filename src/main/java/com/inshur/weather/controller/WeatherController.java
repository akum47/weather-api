package com.inshur.weather.controller;

import com.inshur.weather.response.WeatherResponse;
import com.inshur.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/current-weather")
    public WeatherResponse getCurrentWeather(@RequestParam(name = "lat") @Valid @NotBlank String latitude, @RequestParam(name = "lon") @Valid @NotBlank String longitude) {
        return weatherService.getWeatherForecast(latitude, longitude);
    }
}
