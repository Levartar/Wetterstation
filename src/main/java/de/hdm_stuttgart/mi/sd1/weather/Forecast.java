package de.hdm_stuttgart.mi.sd1.weather;


import de.hdm_stuttgart.mi.sd1.weather.cities.Cities;
import de.hdm_stuttgart.mi.sd1.weather.cities.City;
import de.hdm_stuttgart.mi.sd1.weather.model.Weather;

import java.io.InputStream;
import java.util.Arrays;

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
    final API api = new API();
    final Searcher searcher = new Searcher();

    final String searchString = io.readQueryString();

    try {
    final City[]  matchingCities = searcher.searchCity(searchString);

    final City selectedCity = io.chooseCity(matchingCities);

    final Weather  weather = api.getCityWeatherdata(selectedCity);

    io.displayWeather(weather);

    } catch (Exception e) {
      io.displayException(e);
    }

  }
}

    /*
    // Tests für die Methoden, die ich in UserIO geschrieben habe.
    System.out.println(getSomeCities().toString());
    final UserIO userIO = new UserIO();
    try {
      userIO.chooseCity(getSomeCities());
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    try {
      userIO.displayWeather(WeatherData());
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
    */
/*
  public static Weather WeatherData() {
    final WeatherDataParser weatherDataParser = new WeatherDataParser();
    try {
      final InputStream fileStream = Forecast.class.getClassLoader().getResourceAsStream("stuttgart.weather.json");
      final Weather weather = weatherDataParser.parseStream(fileStream);
      return weather;
    } catch (Exception e){
      Common.exitOnError(e.getMessage());
    }
    return null;
  }


  public static City[] getSomeCities() {
    City[] cities = new Cities().cities;
    return Arrays.copyOfRange(cities, 0, 5);
  }
}*/