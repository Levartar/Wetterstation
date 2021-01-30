package de.hdm_stuttgart.mi.sd1.weather;

import de.hdm_stuttgart.mi.sd1.weather.cities.Cities;
import de.hdm_stuttgart.mi.sd1.weather.cities.City;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


public class SearcherTest {

    /**
     * Test für den Fall das die eingegebene Zeichenkette keine Stadt darstellt, die in der Städteliste genant ist.
     * Geprüft wird, ob die richtige Fehlermeldung erscheint.
     */
    @Test
    public void testSearchCityNoCityFoundLowercase() {
        Searcher searcher = new Searcher();
        try {
            searcher.searchCity("abcdefg");
        } catch (Exception e) {
            assertEquals("No cities containing abcdefg found!", e.getMessage());
        }
    }

    /**
     * Test für den Fall das die eingegebene Zeichenkette keine Stadt darstellt, die in der Städteliste genant ist.
     * Geprüft wird, ob die richtige Fehlermeldung erscheint.
     */
    @Test
    public void testSearchCityNoCityFoundUppercase() {
        Searcher searcher = new Searcher();
        try {
            searcher.searchCity("ABCDEFG");
        } catch (Exception e) {
            assertEquals("No cities containing ABCDEFG found!", e.getMessage());
        }
    }

    /**
     * Test für das erfolgreiche Finden eines Stadtobjektes in unserer Städteliste bei der Übergebung eines Stadtnamens
     * mit Großbuchstaben am Anfang.
     * Geprüft wird ob die Methode das richtige Stadtobjekt zurückgibt.
     */
    @Test
    public void testSearchCitySuccess() {
        Searcher searcher = new Searcher();
        final City[] expectedReturn = new City[1];
        expectedReturn[0] = new Cities().cities[29467];

        try {
            assertArrayEquals(expectedReturn, searcher.searchCity("Köln"));
        } catch (Exception e) {
            // Ignore
        }
    }

    /**
     * Test für das erfolgreiche Finden eines Stadtobjektes in unserer Städteliste bei der Übergebung eines Stadtnamens
     * in Kleinbuchstaben.
     * Geprüft wird ob die Methode das richtige Stadtobjekt zurückgibt.
     */
    @Test
    public void testSearchCitySuccessLowercase() {
        Searcher searcher = new Searcher();
        final City[] expectedReturn = new City[1];
        expectedReturn[0] = new Cities().cities[29467];

        try {
           assertArrayEquals(expectedReturn, searcher.searchCity("köln"));
        } catch (Exception e) {
            // Ignore
        }
    }

    /**
     * Test für das erfolgreiche Finden eines Stadtobjektes in unserer Städteliste bei der Übergebung eines Stadtnamens
     * in Großbuchstaben.
     * Geprüft wird ob die Methode das richtige Stadtobjekt zurückgibt.
     */
    @Test
    public void testSearchCitySuccessUppercase() {
        Searcher searcher = new Searcher();
        final City[] expectedReturn = new City[1];
        expectedReturn[0] = new Cities().cities[29467];

        try {
            assertArrayEquals(expectedReturn, searcher.searchCity("KÖLN"));
        } catch (Exception e) {
            // Ignore
        }
    }

    /**
     * Test falls mehrere Treffer in der Städteliste gefunden werden.
     * Geprüft wird ob das richtige Array mit allen Städten bzw. Stadtbezirken übergeben wird.
     */
    @Test
    public void testSearchCitySuccessMultipleMatches() {
        Searcher searcher = new Searcher();
        final City[] expectedReturn = new City[7];
        final  City[] cities = new Cities().cities;
        expectedReturn[0] = cities[12816];
        expectedReturn[1] = cities[29189];
        expectedReturn[2] = cities[33697];
        expectedReturn[3] = cities[33698];
        expectedReturn[4] = cities[110822];
        expectedReturn[5] = cities[110937];
        expectedReturn[6] = cities[196045];

        try {
            assertArrayEquals(expectedReturn, searcher.searchCity("Stuttgart"));
        } catch (Exception e) {
            // Ignore
        }
    }
}
