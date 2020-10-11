package pl.devtommy;

import com.google.gson.Gson;
import javafx.scene.image.Image;
import pl.devtommy.model.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class WeatherManager {
    private String ICON_WEATHER_PATH = "/pl/devtommy/Icon/weather";
    private WeatherProvider weatherProvider;
    private City city;
    private City selectedCity;
    private City[] cityList;
    private static HashMap<String, Image> weatherImages = new HashMap<String, Image>();

    public WeatherManager(WeatherProvider weatherProvider) {
        this.weatherProvider = weatherProvider;
        cityList = generateCityList();
        weatherImages = addPathsOfWeatherImages();
    }

    private HashMap<String, Image> addPathsOfWeatherImages() {
        HashMap<String, Image> weatherImages = new HashMap<String, Image>();

        weatherImages.put("Thunderstorm",  new Image(this.getClass().getResourceAsStream( ICON_WEATHER_PATH +
                "/storm_100px.png")));
        weatherImages.put("Drizzle",  new Image(this.getClass().getResourceAsStream(ICON_WEATHER_PATH +
                "/rain_100px.png")));
        weatherImages.put("Rain",  new Image(this.getClass().getResourceAsStream(ICON_WEATHER_PATH +
                "/rain_100px.png")));
        weatherImages.put("Snow",  new Image(this.getClass().getResourceAsStream(ICON_WEATHER_PATH +
                "/snow_100px.png")));
        weatherImages.put("Clear",  new Image(this.getClass().getResourceAsStream(ICON_WEATHER_PATH +
                "/sun_100px.png")));
        weatherImages.put("Clouds",  new Image(this.getClass().getResourceAsStream(ICON_WEATHER_PATH +
                "/cloudy_day_100px.png")));
        weatherImages.put("Other",  new Image(this.getClass().getResourceAsStream(ICON_WEATHER_PATH +
                "/dust_52px.png")));
        return weatherImages;
    }

    public City getCityLocation() {
        return city;
    }

    public static Image getWeatherImage(String mainWeatherCondition) {
        try {
            return weatherImages.get(mainWeatherCondition);
        } catch (Exception e) {
            return weatherImages.get("Other");
        }
    }

    public OneDayWeather getCurrentLeftCityWeather() {
        return weatherProvider.getCurrentWeatherByCity(city);
    }

    public OneDayWeather[] getForecastWeather() {
        return weatherProvider.getForecastWeatherByCity(city);
    }

    public void setCity(City city) {
        if (city != null) {
            this.city = city;
        }
    }

    public City getCity() {
        return city;
    }

    public City getSelectedCity() {
        return selectedCity;
    }

    public void setSelectedCity(City selectedCity) {
        this.selectedCity = selectedCity;
    }

    private City[] generateCityList(){
        Gson gson = new Gson();
        City[] cityList = new City[0];
        try {
            cityList = gson.fromJson(new FileReader("src/main/resources/pl/devtommy/json/city.list.json"),
                    City[].class);
        } catch (FileNotFoundException e) {
            System.out.println("city.list.json not found");
        }
        return cityList;
    }

    public ArrayList<City> getCitiesContainsName(String cityName) {
        ArrayList<City> citiesContains = new ArrayList<City>();
        for (City city: cityList) {
            if (city.getName().equals(cityName)) {
                citiesContains.add(0, city);
            }
            else if (city.getName().contains(cityName)) {
                citiesContains.add(city);
            }
        }
        return citiesContains;
    }
}
