package pl.devtommy.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import pl.devtommy.WeatherManager;
import pl.devtommy.view.ViewFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController2 extends BaseController implements Initializable {

    @FXML
    private ImageView refreshLeftImageView;

    @FXML
    private ImageView closeAppImageView;

    @FXML
    void closeApp(MouseEvent event) {

    }

    @FXML
    void closeAppOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void closeAppOnMouseExited(MouseEvent event) {

    }

    @FXML
    void refreshLeftOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void refreshLeftOnMouseExited(MouseEvent event) {

    }

    @FXML
    void refreshLeftWeather(MouseEvent event) {

    }


    public MainWindowController2(WeatherManager weatherManager, ViewFactory viewFactory, String fxmlName) {
        super(weatherManager, viewFactory, fxmlName);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
