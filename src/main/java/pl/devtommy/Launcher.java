package pl.devtommy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.aksingh.owmjapis.api.APIException;
import pl.devtommy.controller.MainWindowController;
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
        WeatherProvider weatherProvider = new OWMProvider(API_KEY);

        WeatherProviderManager weatherProviderManager = new WeatherProviderManager(weatherProvider);
        showMainWindow(stage, weatherProviderManager);
    }

    private void showMainWindow(Stage stage, WeatherProviderManager weatherProviderManager) throws IOException {
        MainWindowController controller = new MainWindowController(weatherProviderManager);
        scene = new Scene(loadFXML("MainWindow", controller));
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(false);
        stage.show();
    }

    private static Parent loadFXML(String fxml, Object controller) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("view/fxml/" + fxml + ".fxml"));
        fxmlLoader.setController(controller);
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