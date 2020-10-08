package pl.devtommy.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
        BaseController controller = new MainWindowController(weatherProviders, this, "MainWindow.fxml");
        mainStageInit(controller);
    }

    public void showSelectCityLocationWindow(WeatherManager cityWeather) {
        BaseController controller = new SelectCityLocationController(cityWeather);
        stageInit(controller, "Select city", "SelectCityLocationWindow.fxml");
    }

    public Node addCityWindow(WeatherManager cityWeather) {
        BaseController controller = new CityWeatherWindowController(cityWeather);
        Node node = null;
        try {
            node = loadFxml(controller, "CityWeatherWindow.fxml").load();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return node;
    }

    private void stageInit(BaseController controller, String title, String fxmlName) {
        Parent parent;
        try {
            parent = loadFxml(controller, fxmlName).load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Stage stage = new Stage();
        stage.initOwner(ownerStage);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(new Scene(parent));
        stage.setTitle(title);
        stage.showAndWait();
    }

    private void mainStageInit(BaseController baseController) {
        Parent parent;
        try {
            parent = loadFxml(baseController).load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        ownerStage.setScene(scene);
        ownerStage.initStyle(StageStyle.TRANSPARENT);
        ownerStage.setResizable(false);
        ownerStage.show();
    }

    private FXMLLoader loadFxml(BaseController baseController) {
        FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("fxml/" + baseController.getFxmlName()));
        fxmlLoader.setController(baseController);
        return fxmlLoader;
    }

    private FXMLLoader loadFxml(BaseController baseController, String fxmlName) {
        FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("fxml/" + fxmlName));
        fxmlLoader.setController(baseController);
        return fxmlLoader;
    }
}
