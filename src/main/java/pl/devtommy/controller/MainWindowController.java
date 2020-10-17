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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    private static final int CITIES_NUMBER = 2;
    private static final int ZOOM_ICON_SIZE = 2;
    private City[] cities;
    private WeatherProvider weather;
    private double xMainWindowOffset;
    private double yMainWindowOffset;


    @FXML
    private ImageView closeIcon;

    @FXML
    private HBox citiesWeatherHbox;

    @FXML
    private Label currentDate;

    public MainWindowController(WeatherProvider weather) {
        this.weather = weather;
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
        zoomIn(closeIcon, ZOOM_ICON_SIZE);
    }

    @FXML
    void closeIconOnMouseExited() {
        zoomOut(closeIcon, ZOOM_ICON_SIZE);
    }

    @FXML
    void refreshWeather() {
        updateCurrentDate();
    }

    private void updateCurrentDate() {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("EE dd MMM yyyy - [HH:mm] ").localizedBy(Locale.US);
        String today = LocalDateTime.now().format(formatter);
        currentDate.setText(today);
    }

    private void addCitiesWeathers() {
        cities = getCitiesLocation();
        for (City city : cities) {
            int maxForecastDays = weather.getMaxForecastDays();
            CityWeather cityWeather = new CityWeather(weather);
            cityWeather.setCity(city);
            citiesWeatherHbox.getChildren().add(ViewFactory.addCityWindow(cityWeather, maxForecastDays));
        }
    }

    private City[] getCitiesLocation() {
        City[] cities = new City[CITIES_NUMBER];
        cities[0] = createExampleCity();
        return cities;
    }

    private City createExampleCity() {
        return new City(7533329, "", "PL", new Coord(0.0, 0.0));
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
