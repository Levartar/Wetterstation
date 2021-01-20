package de.hdm_stuttgart.mi.sd1.weather;

import org.junit.Assert;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

public class APITest {

    @Test public void testUrl(){
        Assert.assertEquals("https://api.openweathermap.org/data/2.5/forecast?lang=de&APPID=1243fb71d2704f4b3e92b4f04f5075cd&units=metric&id=707860",
                API.createUrl(707860));
    }

    @Test public void testcreateFile() {
        try {
            URI link = new URI(API.createUrl(707860));
            Assert.assertEquals("CityWeatherInfo.json", API.createFile(link));
        } catch (Exception e){
            System.out.println("Error");
        }

    }
}
