package de.hdm_stuttgart.mi.sd1.weather;

import org.junit.Assert;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

public class APITest {

    /**
     * Test ob der link Korrekt aus der city id gebaut wird.
     */
    @Test public void testUrl(){
        Assert.assertEquals("https://api.openweathermap.org/data/2.5/forecast?lang=de&APPID=1243fb71d2704f4b3e92b4f04f5075cd&units=metric&id=707860",
                API.createUrl(707860));
    }

    /**
     * Test ob nach ausführen der Methode createUrl eine .json Datei erstellt wurde.
     */
    @Test public void testCreateFile() {
        try {
            URI link = new URI(API.createUrl(707860));
            Assert.assertEquals("CityWeatherInfo.json", API.createFile(link));
        } catch (Exception e){
            System.out.println("Error");
        }

    }

    /**
     * Test mit unvollständiger URI damit keine Verbindung zum Server hergestellt werden kann und damit eine getrennte
     * Internetverbindung simuliert wird.
     * @throws URISyntaxException
     */
    @Test public void testNoConnection() throws URISyntaxException {
        try{
            URI link = new URI("https://api.openweathermap.org/data/2.5/forecast?lang=de&APPID=&units=metric&id=707860");
            API.createFile(link);
        }catch (Exception e){
            Assert.assertEquals("no connection to server please check your Internet connection",e.getMessage());
        }


    }
}
