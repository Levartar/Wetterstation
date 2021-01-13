package de.hdm_stuttgart.mi.sd1.weather;

import de.hdm_stuttgart.mi.sd1.weather.cities.Cities;
import de.hdm_stuttgart.mi.sd1.weather.cities.City;

import java.util.Arrays;

public class Searcher {

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
