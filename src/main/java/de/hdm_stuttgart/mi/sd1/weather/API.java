package de.hdm_stuttgart.mi.sd1.weather;

import de.hdm_stuttgart.mi.sd1.weather.cities.City;
import org.apache.commons.io.IOUtils;

import de.hdm_stuttgart.mi.sd1.weather.model.Weather;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;

public class API {



    public static Weather getCityWeatherdata(City selectedCity) throws URISyntaxException, IOException {
        URI link =new URI(createUrl(selectedCity.getId()));
        return WeatherDataParser.parse( createFile(link));
    }

    public static String createUrl(int id){
        String baseUrl="https://api.openweathermap.org/data/2.5/forecast?lang=de&APPID=1243fb71d2704f4b3e92b4f04f5075cd&units=metric&id="+id;
        return baseUrl;
    }

    public static String createFile(URI link) throws IOException {
        final String pathname="CityWeatherInfo.json";
        String weatherInfo=IOUtils.toString(link,"utf-8");
        File newTextFile = new File(pathname);
        FileWriter fw = new FileWriter(newTextFile);
        fw.write(weatherInfo);
        fw.close();
        return pathname;
    }

}
