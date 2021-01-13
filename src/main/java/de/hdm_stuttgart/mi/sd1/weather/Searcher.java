package de.hdm_stuttgart.mi.sd1.weather;

import de.hdm_stuttgart.mi.sd1.weather.cities.Cities;
import de.hdm_stuttgart.mi.sd1.weather.cities.City;

public class Searcher {

    public static City[] searchCity(String searchString) throws Exception {
        if (searchString.toCharArray().length <= 2) {

        }


        City[] cities = new Cities().cities;

        for (City city : cities) {
            if (city.getName().toLowerCase().equals(searchString.toLowerCase())) {
                return cities;
            }
        }
        return null;
    }
}
