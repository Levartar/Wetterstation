package de.hdm_stuttgart.mi.sd1.weather.cities;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import static de.hdm_stuttgart.mi.sd1.weather.Common.exitOnError;

import java.io.IOException;
import java.io.InputStream;

/**
 * Read list of available cities from file. This list provides a comprehensive grid of places including
 * id values corresponding to https://api.openweathermap.org locations.
 */
public class Cities {

  /**
   * A list of cities / regions. {@link City#getId()} values correspond to
   * https://openweathermap.org data locations.
   */
  static public final City[] cities;

  static {
    City[] citiesTmp = null;
    try(final InputStream is =    // See http://bulk.openweathermap.org/sample/
            Cities.class.getClassLoader().getResourceAsStream("cities.list.json")) {

      final JsonParser parser = new JsonFactory().createParser(is);
      citiesTmp = new ObjectMapper().readValue(parser, new TypeReference<City[]>() {
      });
    } catch (IOException e) {
      exitOnError("Unable to read list of cities, exiting: " + e);
    }
    cities = citiesTmp;
  }
}