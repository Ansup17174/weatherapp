package org.marcel.services;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.marcel.weatherclasses.WeatherResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class OnetWeatherService implements WeatherService {

    public WeatherResponse getWeather(String place, LocalDate date) throws IOException {

        Document googleSite = Jsoup.connect("https://www.google.pl/search?q=pogoda " + place).get();
        Elements resultDivs = googleSite.select("div.r");
        Elements links = resultDivs.select("a[href][ping]");
        links.stream()
                .map(link -> link.attr("href"))
                .filter(link -> !"#".equals(link) && !links.contains("google"))
                .forEach(System.out::println);
        return new WeatherResponse();
    }
}
