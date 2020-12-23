package de.hdm_stuttgart.mi.sd1.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.hdm_stuttgart.mi.sd1.weather.model.Weather;

import java.io.File;
import java.io.IOException;

/**
 * Parse JSON encoded weather data.
 */
public class WeatherDataParser {
  /**
   * Read https://openweathermap.org data from a JSON file
   * @param jsonWeatherDataFilename Filename containing weather data in JSON format
   * @return {@link Weather} corresponding to parsed JSON data
   * @throws IOException As usual: Parsing may fail due to e.g. grammar violation
   */
  static public final Weather parse(final String jsonWeatherDataFilename) throws IOException {
    return new ObjectMapper().readValue(new File(jsonWeatherDataFilename), Weather.class);
  }
}