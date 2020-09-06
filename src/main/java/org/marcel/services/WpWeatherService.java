package org.marcel.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.marcel.weatherclasses.WeatherResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class WpWeatherService {

    public static Optional<WeatherResponse> getResponse(Elements links) throws IOException {
        Optional<Element> wpLink = links.stream()
                .filter(link -> link.attr("href").contains("wp"))
                .findFirst();

        if (wpLink.isPresent()) {

            Document wpPage = Jsoup.connect(wpLink.get().attr("href")).get();

            String temperatureString = wpPage.selectFirst("span.temp").text();
            int temperatureValue = Integer.parseInt(temperatureString);

            String weatherDetails = wpPage.selectFirst("td.detail>div.text").child(0).text();

            Elements weatherInfo = wpPage.select("span[data-v-08147006]");
            List<String> airAndWind = weatherInfo.stream()
                    .filter(span -> span.classNames().size() == 0)
                    .limit(2)
                    .map(span -> span.text())
                    .collect(Collectors.toList());

            int airPressure = Integer.parseInt(airAndWind.get(0));
            int windVelocity = Integer.parseInt(airAndWind.get(1));

            WeatherResponse wpResponse = new WeatherResponse();
            wpResponse.setTemperature(temperatureValue);
            wpResponse.setWeatherState(weatherDetails);
            wpResponse.setWindVelocity(windVelocity);
            wpResponse.setAirPressure(airPressure);

            return Optional.of(wpResponse);

        }

        return Optional.empty();
    }

}
