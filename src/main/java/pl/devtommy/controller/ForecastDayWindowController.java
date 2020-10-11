package pl.devtommy.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import pl.devtommy.model.CityWeather;
import pl.devtommy.model.DayWeather;

public class ForecastDayWindowController {
    private  OneDayWeather forecastDay;

    public ForecastDayWindowController(OneDayWeather forecastDay) {
        this.forecastDay = forecastDay;
    }

    @FXML
    private Label forecastDayOfMonthLabel;

    @FXML
    private ImageView forecastWeatherImageView;

    @FXML
    private Label forecastTempLabel;
}
