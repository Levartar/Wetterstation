package de.hdm_stuttgart.mi.sd1.weather;

import de.hdm_stuttgart.mi.sd1.weather.cities.City;
import de.hdm_stuttgart.mi.sd1.weather.model.List;
import de.hdm_stuttgart.mi.sd1.weather.model.Weather;
import de.hdm_stuttgart.mi.sd1.weather.model.WeatherData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.PrintStream;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


/**
 * Die Klasse "UserIO" enthält alle Methoden, die für das Einlesen der Benutzereingaben und für die Ausgabe
 * der angeforderten Daten benötigt werden.
 */

public class UserIO {

    private static final Logger logger = LogManager.getLogger(UserIO.class);
    private static Scanner scan;
    private static PrintStream printOutput;
    private static PrintStream printErrorOutput;

    public static void setPrintErrorOutput(PrintStream printErrorOutput) {
        UserIO.printErrorOutput = printErrorOutput;
    }

    public void setScanner(Scanner scanner){
        UserIO.scan = scanner;
    }

    public void setPrintOutput(PrintStream printStream){
        UserIO.printOutput = printStream;
    }

    /**
     * Die Methode "welcome Message" ist für die Ausgabe einer Willkomensnachricht sowie der Aufforderung der Eingabe
     * eines Stadtnamens zuständig.
     */

    public static void welcomeMessage(){
        logger.info("Output of a welcome message as well as a prompt to the user to enter a city from which the weather data is requested");
        printOutput.println("Welcome to our weather app");
        printOutput.println("Please enter a city name.");
    }

    /**
     *  Die Methode "readQueryString" ist für das Einlesen der Benutzereingabe zuständig.
     * @return Zurückgegeben wird ein String city, der den Stadtnamen enthält, von dem der Nutzer gerne die Wetterdaten hätte.
     * @throws Exception Wirft einen Fehler falls der Benutzer nichts eingegeben hat.
     */

    public static String readQueryString() throws Exception {
            for (int i=0;i<5;i++){
                final String city = scan.nextLine();
                if (city.equals("")) {
                    logger.warn("The user has not entered anything");
                    printOutput.println("You haven't entered anything. Please enter a city name");

                } else {
                    logger.info("The user has entered: " + city);
                    printOutput.println("You entered: " + city);
                    return city;
                }
            }
        logger.error("User didn't enter anything");
        throw new Exception("User didn't enter anything");
    }

    /**
     * Die Methode "retrySelection" kommt zum Einsatz, falls mehrere Optionen durch die Benutzereingabe zustande kommen
     * und dieser daraus wählen darf. Genauer gesagt ist diese Methode dafür zuständig, bei einer falschen Benutzereingabe
     * nochmal die Möglichkeit bereitzustellen eine Option zu wählen.
     * @param maxAttempts Dieser Parameter steht für die maximalen Versuche, eine Auswahl zu treffen, falls man bei der Eingabe einen Fehler gemacht hat.
     * @param minOption Dieser Parameter steht für die minimalen Auswahloptionen.
     * @param maxOption Dieser Parameter steht für die maximalen Auswahloptionen an bspw. Städten, Bezirken usw..
     * @return Zurückgegeben wird die der Wert  der ausgewählten Option.
     * @throws Exception Wirft einen Fehler wenn die maximale Anzahl an falschen Eingabeversuchen aufgebraucht ist.
     */

    public static int retrySelection(int maxAttempts, int minOption, int maxOption) throws Exception {
        logger.info("There were several matches to the city list. A selection is now available.");
        printOutput.println("Please choose one option, by typing its number.");
        for (int count = 0; count < maxAttempts; count++) {
            try {
                final int value = scan.nextInt();
                if (value >= minOption && value <= maxOption) {
                    return value;
                } else {
                    logger.warn("Selected option: " + value + " is invalid");
                    printOutput.println("Please choose a valid option");
                    scan.reset();
                }
            } catch (Exception e) {
                scan.reset();
            }
        }
        logger.error("Maximum attempts exceeded");
        throw new Exception("Maximum attempts exceeded");
    }

    /**
     * Die Methode "chooseCity" kommt zum Einsatz, falls mehrere Optionen durch die Benutzereingabe zustande kommen
     * und dieser daraus wählen darf.
     * @param cities Dieser Parameter steht für das Array aus Städteoptionen, das auf Basis des eingegebenen Stadtnamens
     *               des Benutzers übergeben wird. Hier sind alle relevanten Optionen enthalten, die zur Suchanfrage passen.
     * @return Zurückgegeben wird die vom Benutzer gewählte Stadtoption.
     * @throws Exception Wirft einen Fehler falls nicht mindestens ein City Objekt übergeben wird.
     */

    public static City chooseCity(City[] cities) throws Exception {
        if (cities.length > 1) {
            int i = 1;
            for (City city : cities) {
                printOutput.println(i + " = " + city.getName() + ", "+city.getCountry());
                i++;
            }
            try {
                final int selectedOption = retrySelection(5, 1, cities.length);
                return cities[selectedOption - 1];
            } catch (Exception e){
                throw e;
            }
        } else if (cities.length == 1) {
            return cities[0];
        } else {
            logger.error("There were no city objects to choose from");
            throw new Exception("Cities must be an array of city objects.");
        }
    }

    /**
     * Die Methode "displayWeather" erzeugt die Augabe der einzelnen Wetterdaten zu der gewählten Stadt.
     * @param weather Dieser Parameter übergibt die Wetterdaten der gewählten Stadt.
     * @throws Exception Wirft einen Fehler falls das Wetterobjekt keine Daten enthält.
     */

    public static void displayWeather(Weather weather) throws Exception {

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("eeee, dd.MM.");
        final DateTimeFormatter timeOutputFormatter = DateTimeFormatter.ofPattern("HH:mm");
        DayOfWeek previousDay = null;

        if (weather.getList() != null) {
            for (List list : weather.getList()) {
                final java.util.List<WeatherData> weatherDataList = list.getWeather();
                final LocalDateTime date = LocalDateTime.parse(list.getDtTxt(), formatter);
                if (date.getDayOfWeek() != previousDay) {
                    printOutput.println("\n"+date.format(outputFormatter));
                }

                printOutput.println(date.format(timeOutputFormatter) + ": " + (int) list.getMain().getTemp() + "°C, " + weatherDataList.get(0).getDescription());
                previousDay = date.getDayOfWeek();
            }
        } else {
            logger.error("The weather object does not contain any data");
            throw new Exception("The weather object does not contain any data");
        }
    }

    /**
     * Die Methode "displayException" gibt Fehlermeldungen aus.
     * @param errorValue ist der Parameter der Exception.
     */

    public static void displayException(Exception errorValue){
        printErrorOutput.println("Exception thrown: "+errorValue.getMessage());
    }
}
