package pl.devtommy.model;

import java.util.ArrayList;
import java.util.List;

public class CityWeather {
    private WeatherProvider weatherProvider;
    private City city;
    private City selectedCity;
    private static final City[] CITY_LIST = Config.getCityList();

    public CityWeather(WeatherProvider weatherProvider) {
        this.weatherProvider = weatherProvider;
    }

    public City getCityLocation() {
        if (city == null) {
            return getDefaultCity();
        } else return city;
    }

    private City getDefaultCity() {
        return new City(0, "Zakynthos", "GR", new Coord(0.0, 0.0));
    }

    public static String getWeatherImageName(String mainWeatherCondition) {
        String weatherConditionName = Config.getWeatherImagesName(mainWeatherCondition);
        if (weatherConditionName != null) {
            return weatherConditionName;
        } else {
            return Config.getWeatherImagesName("Other");
        }
    }

    public DayWeather getCurrentCityWeather() {
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

    public List<City> getCitiesContainsName(String cityName) {
        List<City> citiesContains = new ArrayList<>();
        for (City city: CITY_LIST) {
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
