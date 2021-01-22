package de.hdm_stuttgart.mi.sd1.weather;

import de.hdm_stuttgart.mi.sd1.weather.cities.City;
import de.hdm_stuttgart.mi.sd1.weather.model.Weather;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class API {


    /**
     * getCityWeatherdata bekommt ein City Objekt und gibt die Wetterdaten aus der https://openweathermap.org/api zurück
     * dafür wird erst der Individuelle City Link erstellt, mit dem dann die Individelle Api abfrage gestartet wird.
     * @param selectedCity
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    public static Weather getCityWeatherdata(City selectedCity) throws URISyntaxException, IOException {
        URI link = new URI(createUrl(selectedCity.getId()));
        return WeatherDataParser.parse(createFile(link));
    }

    /**
     * Estellen des Links mit Konstanter Url + Konstanter APPID und variabler id.
     * @param id bekommt die Individuelle City Id
     * @return
     */
    public static String createUrl(int id){
        String baseUrl="https://api.openweathermap.org/data/2.5/forecast?lang=de&APPID=1243fb71d2704f4b3e92b4f04f5075cd&units=metric&id="+id;
        return baseUrl;
    }

    /**
     * createFile schreibt die Wetterdaten aus der Api Abfrage in eine .json Datei
     * @param link
     * @return
     * @throws IOException
     */
    public static String createFile(URI link) throws IOException {
        final String pathname = "CityWeatherInfo.json";
        String weatherInfo=IOUtils.toString(link,"UTF-8");
        File newTextFile = new File(pathname);
        FileWriter fw = new FileWriter(newTextFile);
        fw.write(weatherInfo);
        fw.close();
        return pathname;
    }

}
