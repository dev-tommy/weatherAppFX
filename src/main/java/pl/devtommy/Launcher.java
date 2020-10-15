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
        ViewFactory viewFactory = new ViewFactory(stage, loadWeatherProviders());
        viewFactory.showMainWindow();
    }

    private WeatherProvider[] loadWeatherProviders() {
        String owmApiKey = getApiKeyFromConfigFile("config.properties");
        WeatherProvider[] weatherProviders = new WeatherProvider[1];
        weatherProviders[0] = new OWMProvider(owmApiKey);
        return weatherProviders;
    }

    public static String getApiKeyFromConfigFile(String configFileName) {
        String apiKey = "";
        try (InputStream input = new FileInputStream(configFileName)) {
            Properties prop = new Properties();
            prop.load(input);

            apiKey = prop.getProperty("api.key");

        } catch (IOException ex) {
            System.out.println("OWM API key not found!");
            while(apiKey.length() != 32) {
                System.out.println("API key must be 32 characters long!");
                apiKey = ViewFactory.getApiDialog();
                if (apiKey.isEmpty()) {
                    try {
                        Properties prop = new Properties();
                        prop.load(new FileInputStream(ViewFactory.getFileDialog()));
                        apiKey = prop.getProperty("api.key");
                    } catch (Exception e) {
                        System.out.println("File error");
                        System.exit(123);
                    }
                    break;
                }
            }
        }

        return apiKey;
    }
}