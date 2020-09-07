package pl.devtommy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("MainWindow"));
        scene.getStylesheets().add(loadStyle("style"));
        stage.setScene(scene);
        stage.setTitle("Prognoza pogody");
        stage.setResizable(false);
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    private static String loadStyle(String style) throws IOException {
        return String.valueOf(Launcher.class.getResource(style + ".css"));
    }

    public static void main(String[] args) {
        launch();
    }

}