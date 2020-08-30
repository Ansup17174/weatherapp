package org.marcel.services;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.marcel.weatherclasses.WeatherResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
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


        return new WeatherResponse();
    }


    private Optional<WeatherResponse> onetWeatherResponse(Elements links) throws IOException {
        Optional<Element> onetLink = links.stream()
                .filter(link -> link.attr("href").contains("onet"))
                .findFirst();

        if (onetLink.isPresent()) {
            Document onetPage = Jsoup.connect(onetLink.get().attr("href")).get();
            String temperatureString = onetPage.select("div.temp").first().text();
            int tempValue = Integer.parseInt(temperatureString.replace("°", ""));
            System.out.println(tempValue);
            
        }

        return Optional.empty();
    }
}
