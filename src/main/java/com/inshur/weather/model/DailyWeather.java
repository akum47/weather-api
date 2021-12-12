package com.inshur.weather.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DailyWeather {

    @JsonProperty("dt")
    private long date;

    @JsonProperty("humidity")
    private int humidity;

    @JsonProperty("temp")
    private Temp temp;
}
