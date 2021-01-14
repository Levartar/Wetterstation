package de.hdm_stuttgart.mi.sd1.weather;

import de.hdm_stuttgart.mi.sd1.weather.cities.Cities;
import de.hdm_stuttgart.mi.sd1.weather.cities.City;

import java.util.Arrays;

public class Searcher {

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

        for (City city : cities) {
            if (city.getName().toLowerCase().contains(searchString.toLowerCase())) {
                foundCities=Arrays.copyOf(foundCities, foundCities.length+1);
                foundCities[i]=city;
                i++;
            }
        }
        return foundCities;
    }
}
