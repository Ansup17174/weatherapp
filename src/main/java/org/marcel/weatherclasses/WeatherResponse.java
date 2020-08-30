package org.marcel.weatherclasses;


import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class WeatherResponse {

    private int temperature;

    private String weatherState;

    private double airPressure;

    private int windVelocity;

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getWeatherState() {
        return weatherState;
    }

    public void setWeatherState(String weatherState) {
        this.weatherState = weatherState;
    }

    public double getAirPressure() {
        return airPressure;
    }

    public void setAirPressure(double airPressure) {
        this.airPressure = airPressure;
    }

    public int getWindVelocity() {
        return windVelocity;
    }

    public void setWindVelocity(int windVelocity) {
        this.windVelocity = windVelocity;
    }
}
