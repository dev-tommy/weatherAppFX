package pl.devtommy.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import pl.devtommy.model.CityWeather;
import pl.devtommy.model.DayWeather;

public class ForecastDayWindowController {
    private  OneDayWeather forecastDay;

public class ForecastDayWindowController implements Initializable {
    private DayWeather forecastDay;

    public ForecastDayWindowController(DayWeather forecastDay) {
        this.forecastDay = forecastDay;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshWeather();
        updateDate();
    }

    @FXML
    private Label forecastDayOfMonthLabel;

    @FXML
    private ImageView forecastWeatherImageView;

    @FXML
    private Label forecastTempLabel;
}
