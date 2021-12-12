package com.inshur.weather.service;

import com.inshur.weather.TestUtil;
import com.inshur.weather.client.WeatherClient;
import com.inshur.weather.model.DailyWeather;
import com.inshur.weather.response.WeatherResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceImplTest {

    private static final String MAX_TEMP = "src/test/resources/maxTemp.json";
    private static final String MAX_TEMP_LOW_HUMIDITY = "src/test/resources/maxTempLowHumididy.json";
    private static final String FIRST_DATE_MAX_TEMP_LOW_HUMIDITY = "src/test/resources/firstDateMaxTempLowHumidity.json";

    @Mock
    private WeatherClient weatherClient;

    @InjectMocks
    private WeatherServiceImpl weatherService;

    @Test
    void testFindWarmestTemperature() {
        List<DailyWeather> dailyWeathers = TestUtil.getFile(MAX_TEMP);
        when(weatherClient.getCurrentWeather(anyString(), anyString())).thenReturn(dailyWeathers);
        WeatherResponse weatherResponse = weatherService.getWeatherForecast("89", "09.9");
        assertEquals(Double.parseDouble("283.13"), weatherResponse.getMaxTemperature());
    }

    @Test
    void testFindWarmestTemperatureAndLowestHumidity() {
        List<DailyWeather> dailyWeathers = TestUtil.getFile(MAX_TEMP_LOW_HUMIDITY);
        when(weatherClient.getCurrentWeather(anyString(), anyString())).thenReturn(dailyWeathers);
        WeatherResponse weatherResponse = weatherService.getWeatherForecast("323", "2323");
        assertEquals(7, weatherResponse.getHumidity());
    }

    @Test
    void testFirstDayWithWarmestTemperatureAndLowestHumidity() {
        List<DailyWeather> dailyWeathers = TestUtil.getFile(FIRST_DATE_MAX_TEMP_LOW_HUMIDITY);
        when(weatherClient.getCurrentWeather(anyString(), anyString())).thenReturn(dailyWeathers);
        WeatherResponse weatherResponse = weatherService.getWeatherForecast("313", "123123");
        assertEquals(LocalDate.of(2021,12,11), weatherResponse.getDate());
    }
}
