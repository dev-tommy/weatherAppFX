package pl.devtommy.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import pl.devtommy.WeatherManager;
import pl.devtommy.view.ViewFactory;

public class ForecastDayWindowController extends BaseController{
    @FXML
    private Label forecastDayOfMonthLabel;

    @FXML
    private ImageView forecastWeatherImageView;

    @FXML
    private Label forecastTempLabel;

    public ForecastDayWindowController(WeatherManager weatherManager, ViewFactory viewFactory, String fxmlName) {
        super(weatherManager, viewFactory, fxmlName);
    }
}
