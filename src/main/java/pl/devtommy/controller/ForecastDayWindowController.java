package pl.devtommy.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import pl.devtommy.WeatherManager;

public class ForecastDayWindowController {
    private  WeatherManager cityWeather;
    @FXML
    private Label forecastDayOfMonthLabel;

    @FXML
    private ImageView forecastWeatherImageView;

    @FXML
    private Label forecastTempLabel;

    public ForecastDayWindowController(WeatherManager weatherManager) {
       this.cityWeather = weatherManager;
    }
}
