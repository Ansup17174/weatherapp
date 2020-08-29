package org.marcel.services;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.marcel.weatherclasses.WeatherResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class OnetWeatherService implements WeatherService {

    public WeatherResponse getWeather(String place, LocalDate date) throws IOException {
//        WebClient webClient = new WebClient();
//        webClient.getOptions().setThrowExceptionOnScriptError(false);
//        webClient.getOptions().setJavaScriptEnabled(false);
//        HtmlPage startingPage = webClient.getPage("https://www.google.pl/");
//        HtmlTextInput searchBar = startingPage.getElementByName("q");
//        searchBar.setText(prepareQuerySring(place));
//        HtmlSubmitInput submitSearch = startingPage.getElementByName("btnK");
//        HtmlPage resultPage = submitSearch.click();
//        List<HtmlDivision> resultDivs = resultPage.getByXPath("div[@class='g']");
        return new WeatherResponse();
    }


    private String prepareQuerySring(String name) {
        return "pogoda " + name;
    }
}
