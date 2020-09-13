package org.marcel.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.marcel.weatherclasses.WeatherResponse;

import java.io.IOException;
import java.util.Optional;

public class InteriaWeatherService {

    public static Optional<WeatherResponse> getWeather(Elements links) throws IOException {

        Optional<Element> interiaLink = links.stream()
                .filter(link -> link.attr("href").contains("interia"))
                .findFirst();

        if (interiaLink.isPresent()) {

            Document interiaPage = Jsoup.connect(interiaLink.get().attr("href")).get();

            String temperatureString = interiaPage.selectFirst("div.weather-currently-temp-strict")
                    .text()
                    .replace("°C", "");
            int temperatureValue = Integer.parseInt(temperatureString);

            String weatherDesc = interiaPage.selectFirst("li.weather-currently-icon-description")
                    .text();

            String airPressureString = interiaPage.selectFirst("li.pressure>span")
                    .text();

            int airPressure = Integer.parseInt(airPressureString
                    .replace("hPa", "")
                    .trim());

            String windVelocityString = interiaPage.selectFirst("li.wind>span")
                    .text();

            int windVelocity = Integer.parseInt(windVelocityString
                    .replace("km/h", "")
                    .trim());

            WeatherResponse interiaResponse = new WeatherResponse();

            interiaResponse.setTemperature(temperatureValue);
            interiaResponse.setWeatherState(weatherDesc);
            interiaResponse.setAirPressure(airPressure);
            interiaResponse.setWindVelocity(windVelocity);

            return Optional.of(interiaResponse);
        }

        return Optional.empty();
    }
}
