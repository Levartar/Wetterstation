package de.hdm_stuttgart.mi.sd1.weather;

import de.hdm_stuttgart.mi.sd1.weather.cities.City;
import org.junit.Assert;
import org.junit.Test;

public class SearcherTest {

    @Test
    public void testSearchCity(){
        try{
            final City[] Stuttgart =Searcher.searchCity("Stuttgart");
            final City[] captitalStu=Searcher.searchCity("STUTTGART");
            final City[] Berlin=Searcher.searchCity("Berlin");
            final City[] Washington=Searcher.searchCity("Washington");

            Assert.assertEquals(Stuttgart,Searcher.searchCity("Stuttgart"));
            Assert.assertEquals(captitalStu,Searcher.searchCity("Stuttgart"));
            Assert.assertEquals(Berlin,Searcher.searchCity("Berlin"));
            Assert.assertEquals(Washington,Searcher.searchCity("Washington"));
        }catch (Exception e){
            System.out.println("Testfehler");
        }

    }

}
