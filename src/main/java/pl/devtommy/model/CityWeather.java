package pl.devtommy.model;

import com.google.gson.Gson;
import javafx.scene.image.Image;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CityWeather {
    private static final String ICON_WEATHER_PARENT_PATH = "/pl/devtommy/Icon/weather";
    public static final String CITY_LIST_JSON_PATH = "/pl/devtommy/json/city.list.json";
    private WeatherProvider weatherProvider;
    private City city;
    private City selectedCity;
    private City[] cityList;
    private static Map<String, Image> weatherImages = new HashMap<>();

    public CityWeather(WeatherProvider weatherProvider) {
        this.weatherProvider = weatherProvider;
        cityList = loadCityList();
        weatherImages = addPathsOfWeatherImages();
    }

    private Map<String, Image> addPathsOfWeatherImages() {
        weatherImages.put("Thunderstorm",  new Image(this.getClass().getResourceAsStream( ICON_WEATHER_PARENT_PATH +
                "/storm_100px.png")));
        weatherImages.put("Drizzle",  new Image(this.getClass().getResourceAsStream(ICON_WEATHER_PARENT_PATH +
                "/rain_100px.png")));
        weatherImages.put("Rain",  new Image(this.getClass().getResourceAsStream(ICON_WEATHER_PARENT_PATH +
                "/rain_100px.png")));
        weatherImages.put("Snow",  new Image(this.getClass().getResourceAsStream(ICON_WEATHER_PARENT_PATH +
                "/snow_100px.png")));
        weatherImages.put("Clear",  new Image(this.getClass().getResourceAsStream(ICON_WEATHER_PARENT_PATH +
                "/sun_100px.png")));
        weatherImages.put("Clouds",  new Image(this.getClass().getResourceAsStream(ICON_WEATHER_PARENT_PATH +
                "/cloudy_day_100px.png")));
        weatherImages.put("Other",  new Image(this.getClass().getResourceAsStream(ICON_WEATHER_PARENT_PATH +
                "/dust_52px.png")));
        return weatherImages;
    }

    public City getCityLocation() {
        if (city == null) {
            return getDefaultCity();
        } else return city;
    }

    private City getDefaultCity() {
        return new City(0, "Zakynthos", "GR", new Coord(0.0, 0.0));
    }

    public static Image getWeatherImage(String mainWeatherCondition) {
        Image weatherConditionImage = weatherImages.get(mainWeatherCondition);
        if (weatherConditionImage != null) {
            return weatherConditionImage;
        } else {
            return weatherImages.get("Other");
        }
    }

    public DayWeather getCurrentLeftCityWeather() {
        return weatherProvider.getCurrentWeatherByCity(city);
    }

    public DayWeather[] getForecastWeather() {
        return weatherProvider.getForecastWeatherByCity(city);
    }

    public void setCity(City city) {
            this.city = city;
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

    private City[] loadCityList(){
        Gson gson = new Gson();
        City[] cityList = new City[0];
        cityList = gson.fromJson(new InputStreamReader(this.getClass().getResourceAsStream(CITY_LIST_JSON_PATH)), City[].class);
        return cityList;
    }

    public List<City> getCitiesContainsName(String cityName) {
        List<City> citiesContains = new ArrayList<>();
        for (City city: cityList) {
            if (city.getName().toLowerCase().equals(cityName.toLowerCase())) {
                addToTopOfList(citiesContains, city);
            }
            else if (city.getName().toLowerCase().contains(cityName.toLowerCase())) {
                addToEndOfList(citiesContains, city);
            }
        }
        return citiesContains;
    }

    private void addToEndOfList(List<City> citiesContains, City city) {
        citiesContains.add(city);
    }

    private void addToTopOfList(List<City> citiesContains, City city) {
        citiesContains.add(0, city);
    }
}
