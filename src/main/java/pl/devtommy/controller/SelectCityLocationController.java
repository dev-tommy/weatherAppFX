package pl.devtommy.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pl.devtommy.model.CityWeather;
import pl.devtommy.model.City;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SelectCityLocationController implements Initializable {

    private CityWeather cityWeather;

    public SelectCityLocationController(CityWeather cityWeather) {
        this.cityWeather = cityWeather;
    }

    @FXML
    private TextField nameTextField;

    @FXML
    private Label foundCountLabel;

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
    private Button cancelButton;

    @FXML
    void doubleClickTableRow(MouseEvent event) {
        if (event.getClickCount() == 2) {
            ok();
        }
    }

    @FXML
    void closeWindow() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void ok() {
        City selectedCity = citiesTableView.getSelectionModel().getSelectedItem();
        if (selectedCity != null) {
            cityWeather.setSelectedCity(selectedCity);
            closeWindow();
        }
    }

    @FXML
    void searchCities() {
        String cityName = nameTextField.getText();
        foundCountLabel.setText("");
        citiesTableView.getItems().clear();

        if (!cityName.isEmpty()) {
            List<City> foundCities = cityWeather.getCitiesContainsName(cityName);
            citiesTableView.getItems().addAll(foundCities);
            foundCountLabel.setText(Messages.FOUND_LOCATIONS_MESSAGE + foundCities.size());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupTableView();
    }

    private void setupTableView() {
        cityIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        cityNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        latitudeCol.setCellValueFactory(new PropertyValueFactory<>("latitude"));
        longitudeCol.setCellValueFactory(new PropertyValueFactory<>("longitude"));
    }
}
