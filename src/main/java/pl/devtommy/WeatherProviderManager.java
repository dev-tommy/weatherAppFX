package pl.devtommy;

import com.google.gson.Gson;
import pl.devtommy.model.City;
import pl.devtommy.model.Coord;
import pl.devtommy.model.OneDayWeather;
import pl.devtommy.model.WeatherProvider;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class WeatherProviderManager {
    private WeatherProvider weatherProvider;
    City leftCity;
    City rightCity;

    public WeatherProviderManager(WeatherProvider weatherProvider) {
        this.weatherProvider = weatherProvider;
    }

    public OneDayWeather getCurrentLeftCityWeather() {
        return weatherProvider.getCurrentWeatherByCity(leftCity);
    }

    public OneDayWeather getCurrentRightCityWeather() {
        return weatherProvider.getCurrentWeatherByCity(rightCity);
    }



    public void setLeftCity(City leftCity) {
        this.leftCity = leftCity;
    }

    public void setRightCity(City rightCity) {
        this.rightCity = rightCity;
    }

    private City[] generateCityList(){
        Gson gson = new Gson();
        City[] cityList = new City[0];
        try {
            cityList = gson.fromJson(new FileReader("src/main/resources/pl/devtommy/json/city.list.json"),
                    City[].class);
        } catch (FileNotFoundException e) {
            System.out.println("city.list.json not found");
            //e.printStackTrace();
        }
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
