package org.marcel.weatherclasses;


import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class WeatherRequest {

    @NotBlank
    private String place;


    private LocalDate date = LocalDate.now();

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
