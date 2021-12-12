package com.inshur.weather.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WeatherClientException extends RuntimeException {

    public WeatherClientException(String message) {
        super(message);
    }
}
