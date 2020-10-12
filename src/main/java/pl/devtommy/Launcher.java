package pl.devtommy;

import javafx.application.Application;
import javafx.stage.Stage;
import net.aksingh.owmjapis.api.APIException;
import pl.devtommy.model.WeatherProvider;
import pl.devtommy.model.weatherproviders.OWMProvider;
import pl.devtommy.view.ViewFactory;

import java.io.*;
import java.util.Properties;

public class Launcher extends Application {
    @Override
    public void start(Stage stage) throws IOException, APIException {
        ViewFactory viewFactory = new ViewFactory(stage, loadWeatherProviders());
        viewFactory.showMainWindow();
    }

    public static void main(String[] args) {
        launch();
    }

    public static String getApiKeyFromConfigFile(String configFileName) {
        String apiKey = null;
        try (InputStream input = new FileInputStream(configFileName)) {
            /* config.properties file:
               api.key=your_owm_api_key
             */

            Properties prop = new Properties();
            prop.load(input);

            apiKey = prop.getProperty("api.key");

        } catch (IOException ex) {
            //ex.printStackTrace();
            System.out.println(configFileName + " not found!");

            try {
                Properties prop = new Properties();
                prop.load(new FileInputStream(ViewFactory.getFileDialog()));

                apiKey = prop.getProperty("api.key");
            } catch (Exception e) {
                System.out.println("File error");
                System.exit(123);
            }
        }

        return apiKey;
    }

    private WeatherProvider[] loadWeatherProviders() {
        String owmApiKey;
        WeatherProvider[] weatherProviders = new WeatherProvider[1];
        owmApiKey = getApiKeyFromConfigFile("config.properties");
        weatherProviders[0] = new OWMProvider(owmApiKey);
        return weatherProviders;
    }

}