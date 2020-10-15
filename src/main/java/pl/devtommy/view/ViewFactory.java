package pl.devtommy.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pl.devtommy.model.CityWeather;
import pl.devtommy.Launcher;
import pl.devtommy.controller.*;
import pl.devtommy.model.DayWeather;
import pl.devtommy.model.WeatherProvider;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class ViewFactory {
    private WeatherProvider weatherProvider;
    private static Stage ownerStage;

    public ViewFactory(Stage stage, WeatherProvider weatherProvider) {
        this.weatherProvider = weatherProvider;
        this.ownerStage = stage;
    }

    public void showMainWindow() {
        MainWindowController controller = new MainWindowController(weatherProvider);
        mainStageInit(controller, "MainWindow.fxml");
    }

    public static void showSelectCityLocationWindow(CityWeather cityWeather) {
        SelectCityLocationController controller = new SelectCityLocationController(cityWeather);
        stageInit(controller, "Select city", "SelectCityLocationWindow.fxml");
    }

    public static Node addCityWindow(CityWeather cityWeather, int maxForecastWeather) {
        CityWeatherWindowController controller = new CityWeatherWindowController(cityWeather, maxForecastWeather);
        return getNode(controller, "CityWeatherWindow.fxml");
    }

    private static Node getNode(Object controller, String fxmlName) {
        Node node = null;
        try {
            node = loadFxml(controller, fxmlName).load();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return node;
    }

    public static Node addForecastDay(DayWeather forecastDay) {
        ForecastDayWindowController controller = new ForecastDayWindowController(forecastDay);
        return getNode(controller, "ForecastDayWindow.fxml");
    }

    public static File getFileDialog() {
        File existDirectory;
        try {
            existDirectory = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath());
        } catch (Exception e) {
            existDirectory = new File(System.getProperty("user.home"));
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Config File");
        fileChooser.setInitialDirectory(existDirectory);
        fileChooser.setInitialFileName("config.properties");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Config Files", "*.properties"));
        File selectedFile = fileChooser.showOpenDialog(ownerStage);
        return selectedFile;
    }

    public static String getApiDialog() {
        String apiKey = "";
        TextInputDialog dialog = new TextInputDialog("...");
        dialog.setTitle("Get Weather Provider API key");
        dialog.setHeaderText("File config.properties not found! The weather provider requires a valid API key to run!");
        dialog.setContentText("Please enter API key:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            apiKey = result.get();
        }
        return apiKey;
    }

    private static void stageInit(Object controller, String title, String fxmlName) {
        Node parent = getNode(controller, fxmlName);
        if (parent == null) return;

        Stage stage = new Stage();
        stage.initOwner(ownerStage);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(new Scene((Parent)parent));
        stage.setTitle(title);
        stage.showAndWait();
    }

    private void mainStageInit(Object controller, String fxmlName) {
        Node parent = getNode(controller, fxmlName);
        if (parent == null) return;

        Scene scene = new Scene((Parent) parent);
        scene.setFill(Color.TRANSPARENT);
        ownerStage.setScene(scene);
        ownerStage.initStyle(StageStyle.TRANSPARENT);
        ownerStage.setResizable(false);
        ownerStage.show();
    }

    private static FXMLLoader loadFxml(Object controller, String fxmlName) {
        FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("fxml/" + fxmlName));
        fxmlLoader.setController(controller);
        return fxmlLoader;
    }
}
