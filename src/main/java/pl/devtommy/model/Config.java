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

    public static void store(){
        saveProperties(createProperties());
    }

    private static void loadProperties(InputStream input) throws IOException {
        Properties prop = new Properties();
        prop.load(input);

        apiKey = prop.getProperty("owm.api.key");
        citiesNumber = Integer.parseInt(prop.getProperty("cities.number", String.valueOf(citiesNumber)));

        cities = new City[citiesNumber];
        for (int i = 0; i < citiesNumber; i++) {
            int cityId = Integer.parseInt(prop.getProperty("city." + i + ".id", defaultCityId));
            cities[i] = new City(cityId, "", "", new Coord(0.0, 0.0));
        }
        input.close();
    }

    private static void saveProperties(Properties props) {
        File configFile = new File(Paths.CONFIG_PROPERTIES_PATH);
        FileWriter writer = null;
        try {
            writer = new FileWriter(configFile);
        } catch (IOException e) {
            System.err.println(Messages.CREATE_PROPERTIES_FILE_ERROR_MESSAGE);
            System.exit(1);
        }
        try {
            props.store(writer, Messages.DEFAULT_SETTINGS_MESSAGE);
            writer.close();
        } catch (IOException e) {
            System.err.println(Messages.STORE_PROPERTIES_ERROR_MESSAGE);
            System.exit(1);
        }
    }

    private static Properties createProperties() {
        Properties prop = new Properties();
        prop.setProperty("owm.api.key", apiKey);
        prop.setProperty("cities.number", String.valueOf(citiesNumber));
        for (int i = 0; i < citiesNumber; i++) {
            prop.setProperty("city." + i +".id", String.valueOf(cities[i].getId()));
        }
        return prop;
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
