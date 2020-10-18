package pl.devtommy.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pl.devtommy.model.*;
import pl.devtommy.Launcher;
import pl.devtommy.controller.*;

import java.io.IOException;
import java.util.Optional;

public class ViewFactory {
    private WeatherProvider weatherProvider;
    private static Stage ownerStage;
    private static Config config;

    public ViewFactory(Stage stage, WeatherProvider weatherProvider, Config config) {
        this.weatherProvider = weatherProvider;
        this.ownerStage = stage;
        this.config = config;
    }

    public void showMainWindow() {
        MainWindowController controller = new MainWindowController(weatherProvider);
        mainStageInit(controller, "MainWindow.fxml");
    }

    public static void showSelectCityLocationWindow(CityWeather cityWeather) {
        SelectCityLocationController controller = new SelectCityLocationController(cityWeather);
        stageInit(controller, "Select city", "SelectCityLocationWindow.fxml");
    }

    public static Node addCityWindow(int index, CityWeather cityWeather, int maxForecastWeather) {
        CityWeatherWindowController controller = new CityWeatherWindowController(index, cityWeather,
                maxForecastWeather);
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

    public static String getApiDialog() {
        String apiKey = "";
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle(Messages.GET_WEATHER_PROVIDER_API_KEY_MESSAGE);
        dialog.setHeaderText(Messages.GET_API_HEADER_TEXT);
        dialog.setContentText(Messages.PLEASE_ENTER_API_KEY_MESSAGE);

        while (apiKey.length() != 32) {
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                apiKey = result.get();
             } else {
                System.err.println(Messages.NO_API_KEY_MESSAGE);
                System.exit(1);
            }
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
