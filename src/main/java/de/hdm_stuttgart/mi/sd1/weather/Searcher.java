package de.hdm_stuttgart.mi.sd1.weather;

import de.hdm_stuttgart.mi.sd1.weather.cities.Cities;
import de.hdm_stuttgart.mi.sd1.weather.cities.City;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class Searcher {
    private static final Logger logger = LogManager.getLogger(Searcher.class);

    /**
     * searchCity vergleicht alle city namen aus der cities.list.json mit dem eingegebenen String.
     * Enthält der city Name den String wird das City Objekt in ein neues City Array foundCities geschrieben.
     * Die Länge des foundCities Array wird mit jeder weiteren gefundenen Stadt um 1 erweitert
     * @param searchString
     * @return
     * @throws Exception
     */
    public static City[] searchCity(String searchString) throws Exception {

        City[] cities = new Cities().cities;
        City[] foundCities = new City[0];
        int i=0;
        logger.info("Search city list for hits");
        for (City city : cities) {
            if (city.getName().toLowerCase().contains(searchString.toLowerCase())) {
                foundCities=Arrays.copyOf(foundCities, foundCities.length+1);
                foundCities[i]=city;
                i++;
            }
        }
        if (i==0){
            Exception notFound = new Exception("No cities containing " + searchString + " found!");
            logger.error("No matching cities", notFound);
            throw notFound;
        } else {
            return foundCities;
        }

    }
}
