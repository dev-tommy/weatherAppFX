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

    public String getConfigPath() {
        return configPath;
    }

    public String getApiKey() {
        String apiKey = "";
        try (InputStream input = new FileInputStream(Paths.CONFIG_PROPERTIES_PATH)) {
            Properties prop = new Properties();
            prop.load(input);

            apiKey = prop.getProperty("api.key");

        } catch (IOException ex) {
            while(apiKey.length() != 32) {
                apiKey = ViewFactory.getApiDialog();
                if (apiKey.isEmpty()) {
                    try {
                        Properties prop = new Properties();
                        prop.load(new FileInputStream(ViewFactory.getFileDialog()));
                        apiKey = prop.getProperty("api.key");
                    } catch (Exception e) {
                        System.err.println(Messages.FILE_ERROR_MESSAGE);
                        System.exit(1);
                    }
                    break;
                }
            }
        }

        return apiKey;
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
