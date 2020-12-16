package de.hdm_stuttgart.mi.sd1.weather;

import de.hdm_stuttgart.mi.sd1.weather.cities.Cities;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test parsing list of cities in JSON format.
 */
public class CityParserTest {
  /**
   * Test for number of cities parsed: There are 209579 cities provided by
   * file resources/city.list.json
   */
  @Test
  public void testParsedCityCount() {
    Assert.assertEquals(209579, Cities.cities.length);
  }
}