package pl.devtommy.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.jetbrains.annotations.Nullable;
import pl.devtommy.Launcher;
import pl.devtommy.WeatherManager;
import pl.devtommy.controller.*;
import pl.devtommy.model.WeatherProvider;

import java.io.IOException;

public class ViewFactory {
    private WeatherProvider[] weatherProviders;
    private Stage ownerStage;

    public ViewFactory(Stage stage, WeatherProvider[] weatherProviders) {
        this.weatherProviders = weatherProviders;
        this.ownerStage = stage;
    }

    public void showMainWindow() {
        MainWindowController controller = new MainWindowController(weatherProviders);
        mainStageInit(controller, "MainWindow.fxml");
    }

    public void showSelectCityLocationWindow(WeatherManager cityWeather) {
        SelectCityLocationController controller = new SelectCityLocationController(cityWeather);
        stageInit(controller, "Select city", "SelectCityLocationWindow.fxml");
    }

    public static Node addCityWindow(WeatherManager cityWeather, int maxForecastWeather) {
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

    public static Node addForecastDay(WeatherManager cityWeather) {
        ForecastDayWindowController controller = new ForecastDayWindowController(cityWeather);
        return getNode(controller, "ForecastDayWindow.fxml");
    }

    private void stageInit(Object controller, String title, String fxmlName) {
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
