package pl.devtommy;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.aksingh.owmjapis.api.APIException;
import pl.devtommy.model.WeatherProvider;
import pl.devtommy.model.weatherproviders.OWMProvider;
import pl.devtommy.view.ViewFactory;

import java.io.*;
import java.util.Properties;

public class Launcher extends Application {

    private static Scene scene;
    private static String API_KEY;

    @Override
    public void start(Stage stage) throws IOException, APIException {
        API_KEY = getApiKeyFromConfigFile("config.properties");
        WeatherProvider weatherProvider = new OWMProvider(API_KEY);

        WeatherManager weatherManager = new WeatherManager(weatherProvider);
        ViewFactory viewFactory = new ViewFactory(stage, weatherManager);
        viewFactory.showMainWindow();
    }

    public static void main(String[] args) {
        launch();
    }

    private static String getApiKeyFromConfigFile(String configFileName) {
        String apiKey = null;
        try (InputStream input = new FileInputStream(configFileName)) {
            /* config.properties:
               api.key=your_owm_api_key
             */

            Properties prop = new Properties();
            prop.load(input);

            apiKey = prop.getProperty("api.key");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return apiKey;
    }

}