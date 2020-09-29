package pl.devtommy.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.devtommy.WeatherProviderManager;
import pl.devtommy.model.City;

public class SelectCityLocationController {

    WeatherProviderManager weatherProviderManager;
    City selectedCity;

    public SelectCityLocationController(WeatherProviderManager weatherProviderManager) {
        this.weatherProviderManager = weatherProviderManager;
    }

    @FXML
    private TextField idLabel;

    @FXML
    private TextField nameLabel;

    @FXML
    private TableView<City> citiesTableView;

    @FXML
    private TableColumn<City, Integer> cityIdCol;

    @FXML
    private TableColumn<City, String> cityNameCol;

    @FXML
    private TableColumn<City, String> countryCol;

    @FXML
    private TableColumn<City, Double> latitudeCol;

    @FXML
    private TableColumn<City, Double> longitudeCol;

    @FXML
    private TextField latLabel;

    @FXML
    private TextField lonLabel;

    @FXML
    private Button cancelButton;

    @FXML
    void cancel() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void ok() {
        weatherProviderManager.setLeftCity(selectedCity);
    }

    @FXML
    void searchCities() {

    }
}
