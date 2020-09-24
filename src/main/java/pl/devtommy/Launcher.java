package pl.devtommy;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.aksingh.owmjapis.api.APIException;
import pl.devtommy.model.City;
import pl.devtommy.model.WeatherProvider;
import pl.devtommy.model.weatherproviders.OWMProvider;

import java.io.*;
import java.util.Properties;

public class Launcher extends Application {

    private static Scene scene;
    private static String API_KEY;

    @Override
    public void start(Stage stage) throws IOException, APIException {

        API_KEY = getApiKeyFromConfigFile("config.properties");

        //generateCountryJson();

        WeatherProvider owmProvider = new OWMProvider(API_KEY);

        City testCity = new City(7533329, "Września", "", "PL", new Coord(0.0, 0.0));

        System.out.println(owmProvider.getCurrentWeatherByCity(testCity));

        showMainWindow(stage);
    }

    private void showMainWindow(Stage stage) throws IOException {
        scene = new Scene(loadFXML("MainWindow"));
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(false);
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("view/fxml/" + fxml + ".fxml"));
        return fxmlLoader.load();
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