package org.marcel.services;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.marcel.weatherclasses.WeatherResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WeatherService {

    public List<WeatherResponse> getWeather(String place, LocalDate date) throws IOException {

        List<Optional<WeatherResponse>> weatherResponses = new ArrayList<>();

        Document googleSite = Jsoup.connect("https://www.google.pl/search?q=pogoda " + place).get();
        Elements resultDivs = googleSite.select("div.r");
        Elements links = resultDivs.select("a[href][ping]");

        Optional<WeatherResponse> onetResponse = OnetWeatherService.getResponse(links);
        Optional<WeatherResponse> wpResponse = WpWeatherService.getResponse(links);
        Optional<WeatherResponse> interiaResponse = InteriaWeatherService.getWeather(links);

        weatherResponses.add(onetResponse);
        weatherResponses.add(wpResponse);
        weatherResponses.add(interiaResponse);

        return weatherResponses.stream()
                .filter(Optional::isPresent)
                .map(optional -> optional.get())
                .collect(Collectors.toList());
    }
}
