package pl.devtommy;

import com.google.gson.Gson;
import pl.devtommy.model.City;
import pl.devtommy.model.WeatherProvider;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class WeatherProviderManager {
    private WeatherProvider weatherProvider;

    public WeatherProviderManager(WeatherProvider weatherProvider) {
        this.weatherProvider = weatherProvider;
    }

    private City[] generateCityList() throws FileNotFoundException {
        Gson gson = new Gson();
        City[] cityList = gson.fromJson(new FileReader("src/main/resources/pl/devtommy/json/city.list.json"),
                City[].class);
        return cityList;
    }

    private ArrayList<City> getCitiesContainsName(City[] cityList, String cityName) {
        ArrayList<City> citiesContains = new ArrayList<City>();
        for (City city: cityList) {
            if (city.getName().contains(cityName)) {
                citiesContains.add(city);
            }
        }
        return citiesContains;
    }

    private ArrayList<City> getCitiesEqualsName(City[] cityList, String cityName) {
        ArrayList<City> citiesEquals = new ArrayList<City>();
        for (City city: cityList) {
            if (city.getName().equals(cityName)) {
                citiesEquals.add(city);
            }
        }
        return citiesEquals;
    }
}
