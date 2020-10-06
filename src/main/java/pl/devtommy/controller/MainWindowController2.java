package pl.devtommy.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import pl.devtommy.WeatherManager;
import pl.devtommy.view.ViewFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController2 extends BaseController implements Initializable {

    @FXML
    private ImageView refreshIcon;

    @FXML
    private ImageView closeIcon;

    @FXML
    private HBox citiesWeatherHbox;

    @FXML
    private Label currentDate;

    @FXML
    void closeApp(MouseEvent event) {

    }

    @FXML
    void closeIconOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void closeIconOnMouseExited(MouseEvent event) {

    }

    @FXML
    void refreshIconOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void refreshIconOnMouseExited(MouseEvent event) {

    }

    @FXML
    void refreshWeather(MouseEvent event) {

    }


    public MainWindowController2(WeatherManager weatherManager, ViewFactory viewFactory, String fxmlName) {
        super(weatherManager, viewFactory, fxmlName);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
