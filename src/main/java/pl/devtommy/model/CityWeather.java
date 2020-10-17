package pl.devtommy.model;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import javafx.scene.image.Image;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class CityWeather {
    private String ICON_WEATHER_PATH = "/pl/devtommy/Icon/weather";
    private WeatherProvider weatherProvider;
    private City city;
    private City selectedCity;
    private City[] cityList;
    private static HashMap<String, Image> weatherImages = new HashMap<String, Image>();

    public CityWeather(WeatherProvider weatherProvider) {
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
        if (city == null) {
            return new City(0, "Zakynthos", "GR", new Coord(0.0, 0.0));
        } else return city;
    }

    public static Image getWeatherImage(String mainWeatherCondition) {
        try {
            return weatherImages.get(mainWeatherCondition);
        } catch (Exception e) {
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
            cityList = gson.fromJson(new InputStreamReader(this.getClass().getResourceAsStream("/pl/devtommy/json" +
                            "/city.list.json")),
                    City[].class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            System.exit(300);
        } catch (JsonIOException e) {
            e.printStackTrace();
            System.exit(300);
        }
        return cityList;
    }

    public ArrayList<City> getCitiesContainsName(String cityName) {
        ArrayList<City> citiesContains = new ArrayList<City>();
        for (City city: cityList) {
            if (city.getName().toLowerCase().equals(cityName.toLowerCase())) {
                citiesContains.add(0, city);
            }
            else if (city.getName().toLowerCase().contains(cityName.toLowerCase())) {
                citiesContains.add(city);
            }
        }
        return citiesContains;
    }
}
