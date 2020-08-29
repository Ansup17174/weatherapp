package org.marcel.controllers;


import org.marcel.weatherclasses.WeatherRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class WeatherController {

    @GetMapping("/get_weather")
    public ResponseEntity getWeather() {
        return new ResponseEntity("sram", HttpStatus.OK);
    }

    @PostMapping("/get_weather")
    public ResponseEntity getWeather(@Valid @RequestBody WeatherRequest weatherRequest) {
        return new ResponseEntity(weatherRequest, HttpStatus.OK);
    }
}
