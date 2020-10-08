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

public class CityWeatherWindowController extends BaseController implements Initializable {

    @FXML
    private HBox forecastHBox;

    @FXML
    private ImageView currentImageView;

    @FXML
    private ImageView refreshImageView;

    @FXML
    private Label currentTempLabel;

    @FXML
    private Label describeLabel;

    @FXML
    private Label countryLabel;

    @FXML
    private Label cityLabel;

    @FXML
    private Label monthLabel;

    @FXML
    private Label dayLabel;

    @FXML
    private ImageView cityImageView;

    @FXML
    private Label humidityLabel;

    @FXML
    private Label pressureLabel;

    @FXML
    private Label tempMinMaxLabel;

    @FXML
    private ImageView closeAppImageView;

    @FXML
    void changeCity(MouseEvent event) {

    }

    @FXML
    void changeCityOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void changeCityOnMouseExited(MouseEvent event) {

    }

    @FXML
    void closeApp(MouseEvent event) {

    }

    @FXML
    void closeAppOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void closeAppOnMouseExited(MouseEvent event) {

    }

    @FXML
    void refreshOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void refreshOnMouseExited(MouseEvent event) {

    }

    @FXML
    void refreshWeather(MouseEvent event) {

    }

    public CityWeatherWindowController(WeatherManager weatherManager, ViewFactory viewFactory, String fxmlName) {
        super(weatherManager, viewFactory, fxmlName);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
