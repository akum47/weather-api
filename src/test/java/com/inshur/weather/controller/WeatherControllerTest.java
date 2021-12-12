package com.inshur.weather.controller;

import com.inshur.weather.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WeatherController.class)
public class WeatherControllerTest {

    private static final String WEATHER_URL = "/current-weather";
    private static final String WEATHER_URL_INVALID = "/current-weather-invalid";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherService weatherService;

    @Test
    public void givenCurrentWeatherURIWithQueryParameter_whenMockMVC_thenResponseOK() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(WEATHER_URL)
                        .param("lat", "50.82")
                        .param("lon", "-30.1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void givenInvalidCurrentWeatherURIWithQueryParameter_whenMockMVC_thenResponseNotFound() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(WEATHER_URL_INVALID)
                        .param("lat", "50.82")
                        .param("lon", "-30.1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
        assertEquals(404, mvcResult.getResponse().getStatus());
    }

    @Test
    public void givenCurrentWeatherURIWithInvalidQueryParameter_whenMockMVC_thenResponseBadRequest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(WEATHER_URL)
                        .param("invalid", "50.82")
                        .param("lon", "-30.1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
        assertEquals(400, mvcResult.getResponse().getStatus());
    }

}