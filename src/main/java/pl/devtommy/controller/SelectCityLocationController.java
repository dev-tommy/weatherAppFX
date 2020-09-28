package pl.devtommy.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import pl.devtommy.model.City;

public class SelectCityLocationController {

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
