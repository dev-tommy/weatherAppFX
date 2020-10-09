package pl.devtommy.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import pl.devtommy.WeatherManager;
import pl.devtommy.model.WeatherProvider;
import pl.devtommy.view.ViewFactory;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    private int CITIES_AMOUNT = 2;
    private int FORECAST_DAYS_AMOUNT = 4;
    private int zoomIconSize = 5;
    private WeatherProvider[] weathers;

    public MainWindowController(WeatherProvider[] weathers) {
        this.weathers = weathers;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addCitiesWeathers();
        refreshWeather();
    }

    @FXML
    private ImageView refreshIcon;

    @FXML
    private ImageView closeIcon;

    @FXML
    private HBox citiesWeatherHbox;

    @FXML
    private Label currentDate;

    @FXML
    void closeApp() {
        System.exit(0);
    }

    @FXML
    void closeIconOnMouseEntered() {
        zoomIn(closeIcon, zoomIconSize);
    }

    @FXML
    void closeIconOnMouseExited() {
        zoomOut(closeIcon, zoomIconSize);
    }

    @FXML
    void refreshIconOnMouseEntered() {
        zoomIn(refreshIcon, zoomIconSize);
    }

    @FXML
    void refreshIconOnMouseExited() {
        zoomOut(refreshIcon, zoomIconSize);
    }

    @FXML
    void refreshWeather() {
        updateCurrentDate();
    }

    private void updateCurrentDate() {
        SimpleDateFormat formatter= new SimpleDateFormat("EE dd MMM yyyy - [HH:mm] ", Locale.US);
        Date date = new Date(System.currentTimeMillis());
        currentDate.setText(formatter.format(date));
    }

    private void addCitiesWeathers() {
        for (int i = 0; i< CITIES_AMOUNT; i++){
            int maxForecastDays = weathers[0].getMaxForecastDays();
            WeatherManager cityWeather = new WeatherManager(weathers[0]);
            citiesWeatherHbox.getChildren().add(ViewFactory.addCityWindow(cityWeather, maxForecastDays));
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
