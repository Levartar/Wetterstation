package de.hdm_stuttgart.mi.sd1.weather;

import de.hdm_stuttgart.mi.sd1.weather.cities.Cities;
import de.hdm_stuttgart.mi.sd1.weather.cities.City;
import de.hdm_stuttgart.mi.sd1.weather.model.Weather;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Unit Tests, die die Methoden für die Ein- und Ausgabe des Benutzers testen.
 */
public class UserIOTest {

    /**
     * Ein Test zur Prüfung der Ausgabe der Willkomensnachricht und der Aufforderung einen Stadtnamen einzugeben.
     */
    @Test
    public void testWelcomeMessage(){
        UserIO io = new UserIO();

        // Write the output in a variable
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream printOutput = new PrintStream(output);
        io.setPrintOutput(printOutput);
        io.welcomeMessage();
        assertEquals("Welcome to our weather app\nPlease enter a city name.\n", output.toString());
    }

    /**
     * Ein Test um zu prüfen ob eine Zeichenkette eingegeben wurde
     * und die erfolgreiche Rückmeldung des Programmes in der die eingegebene Zeichenkette aufgeführt ist.
     */
    @Test
    public void testQueryStringValidInput() {
        UserIO io = new UserIO();

        // Set a new valid input
        ByteArrayInputStream inputStream = new ByteArrayInputStream("Stuttgart".getBytes());
        Scanner scanner = new Scanner(inputStream);
        io.setScanner(scanner);

        // Write the output in a variable
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        io.setPrintOutput(printStream);
        try {
            String city = io.readQueryString();
            assertEquals("Stuttgart", city);
            assertEquals("You entered: Stuttgart\n", outputStream.toString());
        } catch (Exception e) {
            // Ignore
        }
    }

    /**
     * Test für den Fall dass der Benutzer keine Eingabe gemacht hat.
     * Zürückgegeben werden soll eine Nachricht die den Benutzer darüber informiert.
     */
    @Test
    public void testQueryStringNoInput() {
        UserIO io = new UserIO();

        // Set a new input
        ByteArrayInputStream inputStream = new ByteArrayInputStream("\n".getBytes());
        Scanner scanner = new Scanner(inputStream);
        io.setScanner(scanner);

        // Write the output in a variable
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        io.setPrintOutput(printStream);

        try {
            String city = io.readQueryString();
            assertEquals("", city);
            assertEquals("You haven't entered anything. Please enter a city name\n", outputStream.toString());
        } catch (Exception e){
            // Ignore
        }
    }

    /**
     * Test für die rfolgreiche Auswahl einer Stadtoption, falls es mehr übereinstimmungen in der Städteliste mit der
     * eingegebenen Zeichenkette gab.
     */
    @Test
    public void testSuccessfulChooseOnFirstTry() {
        UserIO io = new UserIO();

        // Set a new valid input
        ByteArrayInputStream inputStream = new ByteArrayInputStream("1".getBytes());
        Scanner scanner = new Scanner(inputStream);
        io.setScanner(scanner);

        // Write the output in a variable
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        io.setPrintOutput(printStream);

        try {
            int value = io.retrySelection(5, 1, 5);
            assertEquals("Please choose one option, by typing its number.\n", outputStream.toString());
            assertEquals(1, value);
        } catch (Exception e) {
            // Ignore
        }

    }

    /**
     * Test für den Fall, dass der Benutzer bei einer Auswahloption an Städten zuerst einen ungültigen Wert eingibt,
     * beim zweiten Versuch aber ergfolgreich eine mögliche Option auswählt.
     *
     * Getestet wird die Ausgabe an Nachrichten an den Benutzer sowie der zurückzugebende Wert in Form eines Stadtobjektes.
     */
    @Test
    public void testSuccessfulChooseOnSecondTry() {
        UserIO io = new UserIO();

        // Set a new input
        ByteArrayInputStream inputStream = new ByteArrayInputStream("6\n3\n".getBytes());
        Scanner scanner = new Scanner(inputStream);
        io.setScanner(scanner);

        // Write the output in a variable
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        io.setPrintOutput(printStream);

        try {
           int value = io.retrySelection(5, 1, 5);
            assertEquals("Please choose one option, by typing its number.\nPlease choose a valid option\n", outputStream.toString());
            assertEquals(3, value);
        } catch (Exception e) {
            // Ignore
        }
    }

    /**
     * Test für den Fall das bei der Auswahloption die maximale Möglichkeit an versuchen erreicht wurde.
     * Geprüft wird die Ausgabe an Nachrichen und der wurd der Exception.
     */
    @Test
    public void testMaximumAttemptsExceeded() {
        UserIO io = new UserIO();

        // Set a new invalid input
        ByteArrayInputStream inputStream = new ByteArrayInputStream("6\n6\n6\n6\n6\n6\n".getBytes());
        Scanner scanner = new Scanner(inputStream);
        io.setScanner(scanner);

        // Write the output in a variable
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        io.setPrintOutput(printStream);

        try {
            io.retrySelection(5, 1, 5);
            assertEquals("Please choose one option, by typing its number.\n" +
                            "Please choose a valid option\n" +
                            "Please choose a valid option\n" +
                            "Please choose a valid option\n" +
                            "Please choose a valid option\n" +
                            "Please choose a valid option\n",
                    outputStream.toString());
            fail("Should throw exception when maximum attempts exceeded");
        } catch (Exception e) {
            assertEquals("Maximum attempts exceeded", e.getMessage());
        }
    }

    /**
     * Test für den Fall das ein leeres Array an die Stadtwahl Methode übergeben wird.
     * Geprüft wird die Ausgabe der Fehlernachricht.
     */
    @Test
    public void testChooseCityEmptyArray() {
        UserIO io = new UserIO();

        final City[] emptyArray = new City[0];
        try {
            io.chooseCity(emptyArray);
        } catch (Exception e) {
            assertEquals("Cities must be an array of city objects.", e.getMessage());
        }
    }

    /**
     * Test für den Fall das ein Array mit leeren Stadtobjekten an die Stadtwahl Methode übergeben wird.
     * Geprüft wird die Ausgabe der Fehlernachricht.
     */
    @Test
    public void testChooseCityEmptyCity() {
        UserIO io = new UserIO();

        final City[] emptyCityObject = new City[1];
        emptyCityObject[0] = null;
        try {
            io.chooseCity(emptyCityObject);
        } catch (Exception e) {
            assertEquals("Cities must be an array of city objects.", e.getMessage());
        }
    }

    /**
     * Test für die erfolgreiche Rückgabe des Stadtobjektes der Stadtwahl Methode, falls es nur einen Treffer in der
     * Städteliste gab.
     * Geprüft wird die Rückgabe des Stadtobjektes.
     */
    @Test
    public void testChooseCityOnlyOneOption() {
        UserIO io = new UserIO();

        City[] cities = new Cities().cities;
        City[] testCity = new City[1];
        testCity[0] = cities[0];
        try {
            assertEquals(testCity[0], io.chooseCity(testCity));
        } catch (Exception e) {
            // Ignore
        }
    }

    /**
     * Test für den Fall dass der Benutzer eine Auswahl an Städten ausgegeben bekommt aufgrund seiner
     * Benutzereingabe.
     * Geprüft wird die erfolgreiche Ausgabe der Auflistung sowie die Rückgabe des gewählten Stadtobjektes.
     */
    @Test
    public void testChooseCityMultipleOptions() {
        UserIO io = new UserIO();
        final String expectedOptionOutput = "1 = Hurzuf\n2 = Novinki\n3 = Gorkhā\n" +
                "Please choose one option, by typing its number.\n";

        City[] cities = new Cities().cities;
        City[] multipleOptions = new City[3];
        multipleOptions[0] = cities[0];
        multipleOptions[1] = cities[1];
        multipleOptions[2] = cities[2];

        // Set a new valid input
        ByteArrayInputStream inputStream = new ByteArrayInputStream("2\n".getBytes());
        Scanner scanner = new Scanner(inputStream);
        io.setScanner(scanner);

        // Write the output in a variable
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        io.setPrintOutput(printStream);

        City result = cities[1];
        try {
            City selectedCity = io.chooseCity(multipleOptions);
            assertEquals(result, selectedCity);
            assertEquals(expectedOptionOutput, outputStream.toString());
        } catch (Exception e) {
            // Ignore
        }
    }

    /**
     * Test für die erfolgreiche Ausgabe der Wetterdaten der gewählten Stadt des Benutzers.
     * Geprüft wird die Formatierung und richtige Ausgabe der Wetterdaten.
     */
    @Test
    public void testDisplayWeatherSuccess() {
        UserIO io = new UserIO();

        // Write the output in a variable
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        io.setPrintOutput(printStream);

        final String exprectedWeatherOutput = "\n" +
                "Mittwoch, 15.01.\n" +
                "12:00: 14°C, Klarer Himmel\n" +
                "15:00: 11°C, Mäßig bewölkt\n" +
                "18:00: 8°C, Ein paar Wolken\n" +
                "21:00: 6°C, Mäßig bewölkt\n" +
                "\n" +
                "Donnerstag, 16.01.\n" +
                "00:00: 5°C, Überwiegend bewölkt\n" +
                "03:00: 4°C, Überwiegend bewölkt\n" +
                "06:00: 5°C, Überwiegend bewölkt\n" +
                "09:00: 6°C, Bedeckt\n" +
                "12:00: 10°C, Überwiegend bewölkt\n" +
                "15:00: 8°C, Klarer Himmel\n" +
                "18:00: 5°C, Klarer Himmel\n" +
                "21:00: 3°C, Klarer Himmel\n" +
                "\n" +
                "Freitag, 17.01.\n" +
                "00:00: 2°C, Klarer Himmel\n" +
                "03:00: 1°C, Klarer Himmel\n" +
                "06:00: 1°C, Mäßig bewölkt\n" +
                "09:00: 4°C, Überwiegend bewölkt\n" +
                "12:00: 8°C, Mäßig bewölkt\n" +
                "15:00: 7°C, Klarer Himmel\n" +
                "18:00: 6°C, Überwiegend bewölkt\n" +
                "21:00: 5°C, Leichter Regen\n" +
                "\n" +
                "Samstag, 18.01.\n" +
                "00:00: 5°C, Leichter Regen\n" +
                "03:00: 3°C, Mäßiger Schnee\n" +
                "06:00: 1°C, Bedeckt\n" +
                "09:00: 2°C, Bedeckt\n" +
                "12:00: 3°C, Mäßiger Schnee\n" +
                "15:00: 3°C, Mäßiger Schnee\n" +
                "18:00: 1°C, Bedeckt\n" +
                "21:00: 0°C, Bedeckt\n" +
                "\n" +
                "Sonntag, 19.01.\n" +
                "00:00: 0°C, Bedeckt\n" +
                "03:00: 0°C, Mäßiger Schnee\n" +
                "06:00: 0°C, Mäßiger Schnee\n" +
                "09:00: 0°C, Bedeckt\n" +
                "12:00: 2°C, Bedeckt\n" +
                "15:00: 1°C, Mäßiger Schnee\n" +
                "18:00: 0°C, Bedeckt\n" +
                "21:00: 0°C, Mäßig bewölkt\n" +
                "\n" +
                "Montag, 20.01.\n" +
                "00:00: 0°C, Mäßig bewölkt\n" +
                "03:00: 0°C, Klarer Himmel\n" +
                "06:00: -1°C, Klarer Himmel\n" +
                "09:00: 1°C, Klarer Himmel\n";

        try {
            final Weather weatherStuttgart = WeatherDataParser.parse("src/main/resources/stuttgart.weather.json");
            io.displayWeather(weatherStuttgart);
            assertEquals(exprectedWeatherOutput, outputStream.toString());
        } catch (Exception e) {
            // Ignore
        }
    }

    /**
     * Test für den Fall, dass ein leeres Wetterobjekt übergeben wird und so keine Wetterdaten ausgegeben werden können.
     * Geprüft wird die Ausgabe der Fehlermeldung.
     */
    @Test
    public void testDisplayWeatherNoWeatherObject() {
        UserIO io = new UserIO();

        // Write the output in a variable
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        io.setPrintOutput(printStream);

        try {
            final Weather emptyWeather = new Weather();
            io.displayWeather(emptyWeather);
            fail("Die Methode sollte einen Fehler werfen wenn ein leeres Wetter Objekt übergeben wird");
        } catch (Exception e) {
            assertEquals("Das Weather Objekt enthält keine Daten", e.getMessage());
        }
    }

    /**
     * Test für die Ausgabe der Exceptions.
     */
    @Test
    public void testDisplayException() {
        UserIO io = new UserIO();

        // Write the output in a variable
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        io.setPrintErrorOutput(printStream);

        Exception exception = new Exception("TestMessage");
        io.displayException(exception);

        assertEquals("Exception thrown: TestMessage\n", outputStream.toString());
    }
}
