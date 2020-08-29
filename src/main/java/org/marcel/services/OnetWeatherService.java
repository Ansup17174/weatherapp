package org.marcel.services;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import org.marcel.weatherclasses.WeatherResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;

@Service
public class OnetWeatherService implements WeatherService {

    public WeatherResponse getWeather(String place, LocalDate date) throws IOException {
        WebClient webClient = new WebClient();
        HtmlPage startingPage = webClient.getPage("https://www.google.pl/");
        HtmlTextInput googleSearch = startingPage.getElementByName("q");
        googleSearch.setText(prepareQuerySring(place));
        System.out.println(googleSearch.getText());
        System.out.println(place);
        System.out.println(date);
        return new WeatherResponse();
    }


    private String prepareQuerySring(String name) {
        return "pogoda " + name;
    }
}
