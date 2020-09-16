package pl.devtommy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Launcher extends Application {

    private static Scene scene;
    private static String API_KEY;

    @Override
    public void start(Stage stage) throws IOException {

        getApiKeyFromConfigFile("config.properties");

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

    private static void getApiKeyFromConfigFile(String configFileName) {
        try (InputStream input = new FileInputStream(configFileName)) {
            /* config.properties:
               api.key=your_owm_api_key
             */

            Properties prop = new Properties();
            prop.load(input);

            API_KEY = prop.getProperty("api.key");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}