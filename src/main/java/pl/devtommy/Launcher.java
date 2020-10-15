package pl.devtommy;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.devtommy.model.WeatherProvider;
import pl.devtommy.model.weatherproviders.OWMProvider;
import pl.devtommy.view.ViewFactory;

import java.io.*;
import java.util.Properties;

public class Launcher extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        ViewFactory viewFactory = new ViewFactory(stage, loadWeatherProvider());
        viewFactory.showMainWindow();
    }

    private WeatherProvider loadWeatherProvider() {
        String owmApiKey = getApiKeyFromConfigFile("config.properties");
        WeatherProvider weatherProvider = new OWMProvider(owmApiKey);
        return weatherProvider;
    }

    private static String getApiKeyFromConfigFile(String configFileName) {
        String apiKey = "";
        try (InputStream input = new FileInputStream(configFileName)) {
            Properties prop = new Properties();
            prop.load(input);

            apiKey = prop.getProperty("api.key");

        } catch (IOException ex) {
            System.err.println("OWM API key not found!");
            while(apiKey.length() != 32) {
                System.err.println("API key must be 32 characters long!");
                apiKey = ViewFactory.getApiDialog();
                if (apiKey.isEmpty()) {
                    try {
                        Properties prop = new Properties();
                        prop.load(new FileInputStream(ViewFactory.getFileDialog()));
                        apiKey = prop.getProperty("api.key");
                    } catch (Exception e) {
                        System.err.println("File error");
                        System.exit(1);
                    }
                    break;
                }
            }
        }

        return apiKey;
    }
}