package pl.devtommy.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import pl.devtommy.model.CityWeather;
import pl.devtommy.model.City;
import pl.devtommy.model.DayWeather;
import pl.devtommy.view.ViewFactory;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ResourceBundle;

public class CityWeatherWindowController implements Initializable {

    private int forecastDaysNumber;
    private static final int ZOOM_ICON_SIZE = 2;
    private final CityWeather cityWeather;
    private City city;
    private DayWeather dayWeather;

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

    public CityWeatherWindowController(CityWeather cityWeather, int maxForecastDays) {
        this.cityWeather = cityWeather;
        this.forecastDaysNumber = maxForecastDays;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        city = getCityLocation();
        refreshWeather();
    }

    @FXML
    void changeCity() {
        ViewFactory.showSelectCityLocationWindow(cityWeather);
        City newCity = cityWeather.getSelectedCity();
        if (newCity != null || city.getId() == newCity.getId()) {
            city = newCity;
            refreshWeather();
        }
    }

    @FXML
    void refreshWeather() {
        setCityLocation();
        updateDates();
        updateWeatherView();
        updateWeatherImages();
        updateForecastWeather();
    }

    @FXML
    void changeCityOnMouseEntered() {
        zoomIn(cityImageView, ZOOM_ICON_SIZE);
    }

    @FXML
    void changeCityOnMouseExited() {
        zoomOut(cityImageView, ZOOM_ICON_SIZE);
    }

    @FXML
    void refreshOnMouseEntered() {
        zoomIn(refreshImageView, ZOOM_ICON_SIZE);
    }

    @FXML
    void refreshOnMouseExited() {
        zoomOut(refreshImageView, ZOOM_ICON_SIZE);
    }

    private void updateDates() {
        LocalDateTime today = LocalDateTime.now();
        int day = today.getDayOfMonth();
        Month month = today.getMonth();
        DayOfWeek dayOfWeek = today.getDayOfWeek();

        monthLabel.setText(month.name().substring(0,1).toUpperCase() + month.name().substring(1,3).toLowerCase());
        dayLabel.setText(dayOfWeek.name().substring(0,1).toUpperCase() + dayOfWeek.name().substring(1,3).toLowerCase() +
                " " + day);
    }

    private void setCityLocation() {
        cityWeather.setCity(city);
    }

    private City getCityLocation() {
        return cityWeather.getCityLocation();
    }

    private void updateForecastWeather() {
        forecastHBox.getChildren().clear();
        addForecastDays();

    }

    private void updateWeatherImages() {
        String currentWeatherMainCondition = dayWeather.getMainCondition();
        currentImageView.setImage(CityWeather.getWeatherImage(currentWeatherMainCondition));
    }

    private void updateWeatherView() {
        dayWeather = cityWeather.getCurrentLeftCityWeather();
        cityLabel.setText(dayWeather.getName());
        countryLabel.setText(dayWeather.getCountry());
        currentTempLabel.setText( dayWeather.getTemp());
        humidityLabel.setText(dayWeather.getHumidity());
        pressureLabel.setText(dayWeather.getPressure());
        tempMinMaxLabel.setText(
                dayWeather.getTempMin() +
                        " / " +
                        dayWeather.getTempMax()
        );
        describeLabel.setText(dayWeather.getDescription());
    }

    private void addForecastDays() {
        for (int i = 0; i < forecastDaysNumber; i++) {
            DayWeather forecastDay = cityWeather.getForecastWeather()[i];
            forecastHBox.getChildren().add(ViewFactory.addForecastDay(forecastDay));
        }
    }

    private void zoomIn(ImageView imageView, int size){
        imageView.setFitWidth(imageView.getFitWidth()+2*size);
        imageView.setFitHeight(imageView.getFitHeight()+2*size);
        imageView.setLayoutX(imageView.getLayoutX()-size);
        imageView.setLayoutY(imageView.getLayoutY()-size);
    }

    private void zoomOut(ImageView imageView, int size){
        imageView.setFitWidth(imageView.getFitWidth()-2*size);
        imageView.setFitHeight(imageView.getFitHeight()-2*size);
        imageView.setLayoutX(imageView.getLayoutX()+size);
        imageView.setLayoutY(imageView.getLayoutY()+size);
    }

}
