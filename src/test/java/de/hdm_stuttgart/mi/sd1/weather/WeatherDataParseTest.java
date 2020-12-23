package de.hdm_stuttgart.mi.sd1.weather;

import de.hdm_stuttgart.mi.sd1.weather.model.Weather;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class WeatherDataParseTest {

  @Test public void testParseWeatherData() {
    final String sampleWeatherDataFileName = "src/main/resources/stuttgart.weather.json";
    final int expectedForecastCount = 40;
    try {
      final Weather weather = WeatherDataParser.parse(sampleWeatherDataFileName);

      Assert.assertEquals("File '" + sampleWeatherDataFileName +
              "' contains " + expectedForecastCount+ " entries",
          expectedForecastCount, weather.getList().length);
    } catch (final IOException e) {
      Assert.fail("Failed parsing '" + sampleWeatherDataFileName + "': " + e);
    }
  }
}