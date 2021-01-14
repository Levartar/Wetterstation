package de.hdm_stuttgart.mi.sd1.weather;

import de.hdm_stuttgart.mi.sd1.weather.cities.City;
import de.hdm_stuttgart.mi.sd1.weather.model.List;
import de.hdm_stuttgart.mi.sd1.weather.model.Weather;
import de.hdm_stuttgart.mi.sd1.weather.model.WeatherData;


import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


/**
 * Die Klasse "UserIO" enthält alle Methoden, die für das Einlesen der Benutzereingaben und für die Ausgabe
 * der angeforderten Daten benötigt werden.
 */

public class UserIO {

    private static Scanner scan=new Scanner(System.in);
    /**
     * Die Methode "readQueryString" ist für das Einlesen der Benutzereingabe zuständig.
     * @return Zurückgegeben wird ein String city, der den Stadtnamen enthält, von dem der
     * Nutzer gerne die Wetterdaten hätte.
     */

    public static String readQueryString() {
            System.out.println("Please enter a city name.");
            final String city = scan.next();
            if (city == "") {
                System.out.println("You haven't entered anything. Please enter a city name");
            } else {
                System.out.println("You entered: " + city);
            }
            return city;
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

    private static int retrySelection(int maxAttempts, int minOption, int maxOption) throws Exception {
        System.out.println("Please choose one option, by typing its number. ");
        for (int count = 0; count < maxAttempts; count++) {
            try {
                final int value = scan.nextInt();
                if (value >= minOption && value <= maxOption) {
                    return value;
                } else {
                    System.out.println("Please choose a valid option");
                    scan.reset();
                }
            } catch (Exception e) {
                scan.reset();
            }
        }
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
                System.out.println(i + " = " + city.getName());
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
            throw new Exception("Cities must be an array of city objects.");
        }
    }

    /**
     * Die Methode "displayWeather" erzeugt die Augabe der einzelnen Wetterdaten zu der gewählten Stadt.
     * @param weather Dieser Parameter übergibt die Wetterdaten der gewählten Stadt.
     */

    public static void displayWeather(Weather weather) {

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("eeee, dd.MM.");
        final DateTimeFormatter timeOutputFormatter = DateTimeFormatter.ofPattern("HH:mm");
        DayOfWeek previousDay = null;

        for (List list : weather.getList()) {
            final java.util.List<WeatherData> weatherDataList = list.getWeather();
            final LocalDateTime date = LocalDateTime.parse(list.getDtTxt(), formatter);
            if (date.getDayOfWeek() != previousDay) {
                System.out.println("");
                System.out.println(date.format(outputFormatter));
            }
            System.out.println(date.format(timeOutputFormatter) + ": " + (int) list.getMain().getTemp() + "°C, " + weatherDataList.get(0).getDescription());
            previousDay = date.getDayOfWeek();

            /*
            for(WeatherData weatherData : weatherDataList) {
                System.out.println(weatherData.getDescription());
            }
            */
        }
    }

    public static void displayException(Exception errorvalue){
        System.err.println("Exception thrown: "+errorvalue.getMessage());
    }
}
