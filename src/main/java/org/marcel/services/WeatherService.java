package org.marcel.services;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.marcel.weatherclasses.WeatherResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class WeatherService {

    public WeatherResponse getWeather(String place, LocalDate date) throws IOException {

        Document googleSite = Jsoup.connect("https://www.google.pl/search?q=pogoda " + place).get();
        Elements resultDivs = googleSite.select("div.r");
        Elements links = resultDivs.select("a[href][ping]");

        Optional<WeatherResponse> onetResponse = onetWeatherResponse(links);

        if (onetResponse.isPresent()) {
            return onetResponse.get();
        }

        return new WeatherResponse();
    }


    private Optional<WeatherResponse> onetWeatherResponse(Elements links) throws IOException {
        Optional<Element> onetLink = links.stream()
                .filter(link -> link.attr("href").contains("onet"))
                .findFirst();

        if (onetLink.isPresent()) {

            Document onetPage = Jsoup.connect(onetLink.get().attr("href")).get();

            String temperatureString = onetPage.selectFirst("div.temp").text();
            int temperatureValue = Integer.parseInt(temperatureString.replace("°", ""));

            Element weatherDescDiv = onetPage.selectFirst("div.forecastDesc");
            String weatherDesc = weatherDescDiv.text();

            Element weatherParams = onetPage.selectFirst("div.weatherParams>ul");
            List<String> windAndAirPressure = weatherParams.children().stream()
                    .map(li -> li.children())
                    .filter(twoSpans -> "Ciśnienie atmosferyczne".equals(twoSpans.get(0).text())
                    || "Wiatr".equals(twoSpans.get(0).text()))
                    .map(twoSpans -> twoSpans.get(1).text())
                    .collect(Collectors.toList());  // [wind, air pressure]

            int windVelocity = Integer.parseInt(windAndAirPressure.get(0).replace(" km/h", ""));
            int airPressure = Integer.parseInt(windAndAirPressure.get(1).replace(" hPa", ""));

            WeatherResponse onetResponse = new WeatherResponse();
            onetResponse.setTemperature(temperatureValue);
            onetResponse.setWeatherState(weatherDesc);
            onetResponse.setWindVelocity(windVelocity);
            onetResponse.setAirPressure(airPressure);

            return Optional.of(onetResponse);
        }
        return Optional.empty();
    }
}
