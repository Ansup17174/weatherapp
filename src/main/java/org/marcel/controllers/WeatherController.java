package org.marcel.controllers;


import org.marcel.services.WeatherService;
import org.marcel.weatherclasses.WeatherRequest;
import org.marcel.weatherclasses.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
public class WeatherController {

    private WeatherService weatherService;


    @Autowired
    public WeatherController(WeatherService onetWeatherService) {
        this.weatherService = onetWeatherService;
    }

    @GetMapping("/get_weather")
    public ResponseEntity getWeather() {
        return new ResponseEntity("sram", HttpStatus.OK);
    }

    @PostMapping("/get_weather")
    public ResponseEntity getWeather(@Valid @RequestBody WeatherRequest weatherRequest) throws IOException {

        List<WeatherResponse> weatherResponses = weatherService.getWeather(
                weatherRequest.getPlace(),
                weatherRequest.getDate()
        );

        return new ResponseEntity(weatherResponses, HttpStatus.OK);
    }
}
