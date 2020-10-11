package pl.devtommy.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import pl.devtommy.model.CityWeather;
import pl.devtommy.model.City;
import pl.devtommy.model.Coord;
import pl.devtommy.model.WeatherProvider;
import pl.devtommy.view.ViewFactory;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    private int CITIES_AMOUNT = 2;
    private int zoomIconSize = 2;
    private City[] cities;
    private WeatherProvider[] weathers;
    private double xMainWindowOffset;
    private double yMainWindowOffset;


    @FXML
    private ImageView closeIcon;

    @FXML
    private HBox citiesWeatherHbox;

    @FXML
    private Label currentDate;

    public MainWindowController(WeatherProvider[] weathers) {
        this.weathers = weathers;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addCitiesWeathers();
        refreshWeather();
    }

    @FXML
    void closeApp() {
        System.exit(0);
    }

    @FXML
    void moveMainWindowOnMouseDragged(MouseEvent event) {
          Stage stage = (Stage) closeIcon.getScene().getWindow();
          stage.setX(event.getScreenX() + xMainWindowOffset);
          stage.setY(event.getScreenY() + yMainWindowOffset);
    }

    @FXML
    void takeMouseOffsetOnMousePressed(MouseEvent event) {
        Stage stage = (Stage) closeIcon.getScene().getWindow();
        xMainWindowOffset = stage.getX() - event.getScreenX();
        yMainWindowOffset = stage.getY() - event.getScreenY();
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
    void refreshWeather() {
        updateCurrentDate();
    }

    private void updateCurrentDate() {
        SimpleDateFormat formatter= new SimpleDateFormat("EE dd MMM yyyy - [HH:mm] ", Locale.US);
        Date date = new Date(System.currentTimeMillis());
        currentDate.setText(formatter.format(date));
    }

    private void addCitiesWeathers() {
        cities = getCitiesLocation();
        for (int i = 0; i< cities.length; i++){
            int maxForecastDays = weathers[0].getMaxForecastDays();
            CityWeather cityWeather = new CityWeather(weathers[0]);
            cityWeather.setCity(cities[i]);
            citiesWeatherHbox.getChildren().add(ViewFactory.addCityWindow(cityWeather, maxForecastDays));
        }
    }

    private City[] getCitiesLocation() {
        City[] cities = new City[CITIES_AMOUNT];
        //example
        cities[0] = new City(7533329, "", "", "PL", new Coord(0.0, 0.0));
        return cities;
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
