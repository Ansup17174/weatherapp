package org.marcel.services;

import org.marcel.weatherclasses.WeatherResponse;

import java.io.IOException;
import java.time.LocalDate;

public interface WeatherService {

    public WeatherResponse getWeather(String place, LocalDate date) throws IOException;

}
