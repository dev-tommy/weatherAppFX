package pl.devtommy.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import pl.devtommy.model.CityWeather;
import pl.devtommy.model.DayWeather;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

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

    private void updateDate() {
        LocalDateTime forecastDate = forecastDay.getDate();
        String day = String.valueOf(forecastDate.getDayOfMonth());
        String dayOfWeek = forecastDate.getDayOfWeek().name().substring(0,3);

        String date = dayOfWeek + " " + day;
        forecastDayOfMonthLabel.setText(date);
    }

    private void refreshWeather() {
        updateWeatherView();
        updateWeatherImages();
    }

    private void updateWeatherImages() {
        String forecastWeatherMainCondition = forecastDay.getMainCondition();
        forecastWeatherImageView.setImage(CityWeather.getWeatherImage(forecastWeatherMainCondition));
    }

    private void updateWeatherView() {
        forecastTempLabel.setText(forecastDay.getTemp());
    }



}
