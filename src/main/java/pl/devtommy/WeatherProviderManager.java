package pl.devtommy;

import com.google.gson.Gson;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import pl.devtommy.controller.SelectCityLocationController;
import pl.devtommy.model.City;
import pl.devtommy.model.Coord;
import pl.devtommy.model.OneDayWeather;
import pl.devtommy.model.WeatherProvider;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class WeatherProviderManager {
    private WeatherProvider weatherProvider;
    City leftCity;
    City rightCity;
    City[] cityList;
    HashMap<String, Image> weatherImages = new HashMap<String, Image>();

    public WeatherProviderManager(WeatherProvider weatherProvider) {
        this.weatherProvider = weatherProvider;
        cityList = generateCityList();
        weatherImages = addPathsOfWeatherImages();
    }

    private HashMap<String, Image> addPathsOfWeatherImages() {
        HashMap<String, Image> weatherImages = new HashMap<String, Image>();

        weatherImages.put("Thunderstorm",  new Image(this.getClass().getResourceAsStream("/pl/devtommy/view/Icon/weather" +
                "/storm_100px.png")));
        weatherImages.put("Drizzle",  new Image(this.getClass().getResourceAsStream("/pl/devtommy/view/Icon/weather" +
                "/rain_100px.png")));
        weatherImages.put("Rain",  new Image(this.getClass().getResourceAsStream("/pl/devtommy/view/Icon/weather" +
                "/rain_100px.png")));
        weatherImages.put("Snow",  new Image(this.getClass().getResourceAsStream("/pl/devtommy/view/Icon/weather" +
                "/snow_100px.png")));
        weatherImages.put("Clear",  new Image(this.getClass().getResourceAsStream("/pl/devtommy/view/Icon/weather" +
                "/sun_100px.png")));
        weatherImages.put("Clouds",  new Image(this.getClass().getResourceAsStream("/pl/devtommy/view/Icon/weather" +
                "/cloudy_day_100px.png")));
        weatherImages.put("Other",  new Image(this.getClass().getResourceAsStream("/pl/devtommy/view/Icon/weather" +
                "/dust_52px.png")));
        return weatherImages;
    }

    public City getLeftSavedCityLocation() {
        return new City(7533329, "Września", "", "PL", new Coord(0.0, 0.0));
    }

    public City getRightSavedCityLocation() {
        return new City(0, "Zakynthos", "", "", new Coord(0.0, 0.0));
    }

    public City getCity() {
        return null;
    }

    public Image getWeatherImage(String mainWeatherCondition) {
        try {
            return weatherImages.get(mainWeatherCondition);
        } catch (Exception e) {
            return weatherImages.get("Other");
        }
    }

    public OneDayWeather getCurrentLeftCityWeather() {
        return weatherProvider.getCurrentWeatherByCity(leftCity);
    }

    public OneDayWeather[] getForecastLeftCityWeather() {
        return weatherProvider.getForecastWeatherByCity(leftCity);
    }

    public OneDayWeather getCurrentRightCityWeather() {
        return weatherProvider.getCurrentWeatherByCity(rightCity);
    }

    public OneDayWeather[] getForecastRightCityWeather() {
        return weatherProvider.getForecastWeatherByCity(rightCity);
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

    private ArrayList<City> getCitiesContainsName(String cityName) {
        ArrayList<City> citiesContains = new ArrayList<City>();
        for (City city: cityList) {
            if (city.getName().contains(cityName)) {
                citiesContains.add(city);
            }
        }
        return citiesContains;
    }

    private ArrayList<City> getCitiesEqualsName(String cityName) {
        ArrayList<City> citiesEquals = new ArrayList<City>();
        for (City city: cityList) {
            if (city.getName().equals(cityName)) {
                citiesEquals.add(city);
            }
        }
        return citiesEquals;
    }
}
