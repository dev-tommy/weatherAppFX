package pl.devtommy.model;

import com.google.gson.Gson;
import pl.devtommy.view.ViewFactory;

import java.io.*;
import java.util.Map;
import java.util.Properties;

public class Config {
    private static int citiesNumber = 2;
    private static final String DEFAULT_CITY_ID = "251280";
    private static String configPath;
    private static String apiKey;
    private static City[] cities;
    private static City[] cityList;
    private static final Map<String, String> WEATHER_IMAGES_NAMES = Map.of(
            "Thunderstorm", "storm_100px.png",
            "Drizzle",  "rain_100px.png",
            "Rain",  "rain_100px.png",
            "Snow",  "snow_100px.png",
            "Clear",  "sun_100px.png",
            "Clouds",  "cloudy_day_100px.png",
            "Other", "dust_52px.png"
    );

    public Config(String configPath) {
        this.configPath = configPath;
        load();
        this.cityList = loadCityList();
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

    public static String getWeatherImagesName(String weatherCondition) {
        return WEATHER_IMAGES_NAMES.get(weatherCondition);
    }

    public static City[] getCityList() {
        return cityList;
    }

    public static void setApiKey(String apiKey) {
        Config.apiKey = apiKey;
    }

    public static void setCity(int i, City city) {
        Config.cities[i] = city;
    }

    public void load(){
        try (InputStream input = new FileInputStream(configPath)) {
            loadProperties(input);
        } catch (IOException ex) {
            saveProperties(createDefaultProperties());
            try (InputStream input = new FileInputStream(configPath)) {
                loadProperties(input);
            } catch (Exception exc) {
                System.err.println(Messages.CREATE_PROPERTIES_FILE_ERROR_MESSAGE);
                System.exit(1);
            }
        }
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
            int cityId = Integer.parseInt(prop.getProperty("city." + i + ".id", DEFAULT_CITY_ID));
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

    private City[] loadCityList(){
        Gson gson = new Gson();
        City[] cityList = new City[0];
        try {
            cityList = gson.fromJson(new InputStreamReader(this.getClass().getResourceAsStream(Paths.CITY_LIST_JSON_PATH)
                    , "UTF-8"), City[].class);
        } catch (UnsupportedEncodingException e) {
            System.err.println(Messages.UNSUPPORTED_ENCODING_EXCEPTION_MESSAGE);
            System.exit(1);
        }
        return cityList;
    }
}
