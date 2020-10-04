package pl.devtommy.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pl.devtommy.WeatherManager;
import pl.devtommy.model.City;
import pl.devtommy.view.ViewFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SelectCityLocationController extends BaseController implements Initializable {

    private ArrayList<City> foundCities;

    public SelectCityLocationController(WeatherManager weatherManager, ViewFactory viewFactory, String fxmlName) {
        super(weatherManager, viewFactory, fxmlName);
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
            weatherManager.setSelectedCity(selectedCity);
            closeWindow();
        }
    }

    @FXML
    void searchCities() {
        String cityName = nameTextField.getText();
        foundCountLabel.setText("");
        citiesTableView.getItems().clear();

        if (!cityName.isEmpty()) {
            foundCities = weatherManager.getCitiesContainsName(cityName);
            citiesTableView.getItems().addAll(foundCities);
            foundCountLabel.setText("Found locations: " + foundCities.size());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupTableView();
    }

    private void setupTableView() {
        cityIdCol.setCellValueFactory(new PropertyValueFactory<City, Integer>("id"));
        cityNameCol.setCellValueFactory(new PropertyValueFactory<City, String>("name"));
        countryCol.setCellValueFactory(new PropertyValueFactory<City, String>("country"));
        latitudeCol.setCellValueFactory(new PropertyValueFactory<City, Double>("latitude"));
        longitudeCol.setCellValueFactory(new PropertyValueFactory<City, Double>("longitude"));
    }
}
