package de.hdm_stuttgart.mi.sd1.weather;


import de.hdm_stuttgart.mi.sd1.weather.cities.City;
import de.hdm_stuttgart.mi.sd1.weather.model.Weather;

import java.util.Scanner;

/**
 * Providing terminal based weather forecast
 */
public class Forecast {

  /**
   * <p>Entry starting the application.</p>
   *
   * @param args Yet unused.
   */
  public static void main(String[] args) throws Exception {

    final UserIO io = new UserIO();

    io.setScanner(new Scanner(System.in));
    io.setPrintOutput(System.out);
    io.setPrintErrorOutput(System.err);

    final API api = new API();
    final Searcher searcher = new Searcher();
    io.welcomeMessage();

    try {
      final String searchString = io.readQueryString();
      final City[]  matchingCities = searcher.searchCity(searchString);

      final City selectedCity = io.chooseCity(matchingCities);

      final Weather  weather = api.getCityWeatherdata(selectedCity);

      io.displayWeather(weather);

    } catch (Exception e) {
      io.displayException(e);
    }

  }
}