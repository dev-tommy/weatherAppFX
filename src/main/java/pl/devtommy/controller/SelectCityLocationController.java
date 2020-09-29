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
    private TextField latLabel;

    @FXML
    private TextField lonLabel;

    @FXML
    private TableView<City> citiesTableView;

    @FXML
    void searchCities() {

    }
}
