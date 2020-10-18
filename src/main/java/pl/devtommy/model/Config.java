package pl.devtommy.model;

import pl.devtommy.view.ViewFactory;

import java.io.*;
import java.util.Properties;

public class Config {
    private static int citiesNumber = 2;
    private static String defaultCityId = "251280";
    private static String configPath;
    private static String apiKey;
    private static City[] cities;

    public Config(String configPath) {
        this.configPath = configPath;
    }

    public String getApiKey() {
        return apiKey;
    }

    public static int getCitiesNumber() {
        return citiesNumber;
    }

    public static City[] getCities() {
        return cities;
    }

    public static void setCitiesNumber(int citiesNumber) {
        Config.citiesNumber = citiesNumber;
    }

    public static void setCities(City[] cities) {
        Config.cities = cities;
    }

    public static void setCity(int i, City city) {
        Config.cities[i] = city;
    }

    private static Properties createDefaultProperties() {
        apiKey = ViewFactory.getApiDialog();
        Properties prop = new Properties();
        prop.setProperty("owm.api.key", apiKey);
        prop.setProperty("cities.number", String.valueOf(citiesNumber));
        prop.setProperty("city.0.id", "7533329");
        prop.setProperty("city.1.id", "7533329");
        return prop;
    }
}
