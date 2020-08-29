package org.marcel.weatherclasses;


import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class WeatherResponse {

    private int temperature;

    private WeatherState weatherState;

    private double airPressure;

    private Wind wind;

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public WeatherState getWeatherState() {
        return weatherState;
    }

    public void setWeatherState(WeatherState weatherState) {
        this.weatherState = weatherState;
    }

    public double getAirPressure() {
        return airPressure;
    }

    public void setAirPressure(double airPressure) {
        this.airPressure = airPressure;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }
}
