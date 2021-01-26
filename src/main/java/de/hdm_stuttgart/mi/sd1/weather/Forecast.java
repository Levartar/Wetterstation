package de.hdm_stuttgart.mi.sd1.weather;


import de.hdm_stuttgart.mi.sd1.weather.cities.City;
import de.hdm_stuttgart.mi.sd1.weather.model.Weather;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

  private static final Logger logger = LogManager.getLogger(Forecast.class);

  public static void main(String[] args) throws Exception {

    final UserIO io = new UserIO();

    io.setScanner(new Scanner(System.in));
    io.setPrintOutput(System.out);
    io.setPrintErrorOutput(System.err);

    final API api = new API();
    final Searcher searcher = new Searcher();
    io.welcomeMessage();

    try {
      logger.info("Reading new query string");
      final String searchString = io.readQueryString();
      logger.info("Search for city: " +searchString);
      final City[]  matchingCities = searcher.searchCity(searchString);
      logger.info("Selection of a hit with the city list.");
      final City selectedCity = io.chooseCity(matchingCities);
      logger.info("Request of the weather data of the selected city ");
      final Weather  weather = api.getCityWeatherdata(selectedCity);
      logger.info("Output of the weather data to the user ");
      io.displayWeather(weather);

    } catch (Exception e) {
      logger.error("Output of an error message to the user");
      io.displayException(e);
    }

  }
}