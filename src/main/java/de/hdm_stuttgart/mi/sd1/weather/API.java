package de.hdm_stuttgart.mi.sd1.weather;

import de.hdm_stuttgart.mi.sd1.weather.cities.City;
import de.hdm_stuttgart.mi.sd1.weather.model.Weather;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

public class API {

    private static final Logger logger = LogManager.getLogger(API.class);
    /**
     * getCityWeatherdata bekommt ein City Objekt und gibt die Wetterdaten aus der https://openweathermap.org/api zurück
     * dafür wird erst der Individuelle City Link erstellt, mit dem dann die Individelle Api abfrage gestartet wird.
     * @param selectedCity ausgewählte Stadt
     * @return Wetter Objekt
     * @throws URISyntaxException wirft Exception wenn URI Fehler
     * @throws IOException wirft Exception wenn Connection Fehler
     */
    public static Weather getCityWeatherdata(City selectedCity) throws Exception {
        URI link = new URI(createUrl(selectedCity.getId()));
        return WeatherDataParser.parse(createFile(link));
    }

    /**
     * Estellen des Links mit Konstanter Url + Konstanter APPID und variabler id.
     * @param id bekommt die Individuelle City Id
     * @return Url String
     */
    public static String createUrl(int id){
        logger.info("Creating api url for id: " + id);
        return "https://api.openweathermap.org/data/2.5/forecast?lang=de&APPID=1243fb71d2704f4b3e92b4f04f5075cd&units=metric&id="+id;

    }

    /**
     * createFile schreibt die Wetterdaten aus der Api Abfrage in eine .json Datei
     * @param link Url String
     * @return Pathname = Url String
     * @throws IOException wirft Exception wenn Connection Fehler
     */
    public static String createFile(URI link) throws Exception {
        logger.info("Getting weatherdata from openweatherapi");
        final String pathname = "CityWeatherInfo.json";
        try {
            String weatherInfo = IOUtils.toString(link, StandardCharsets.UTF_8);
        File newTextFile = new File(pathname);
        logger.info("Writing weatherdata to file: " + pathname);
        FileWriter fw = new FileWriter(newTextFile);
        fw.write(weatherInfo);
        fw.close();
        return pathname;

        }catch (Exception e){
            Exception noConnection = new Exception("no connection to server please check your Internet connection");
            logger.error("Connection to API server failed", noConnection);
            throw noConnection;
        }
    }

}
