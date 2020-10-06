package pl.devtommy.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import pl.devtommy.WeatherManager;
import pl.devtommy.view.ViewFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController2 extends BaseController implements Initializable {

    private int zoomIconSize = 5;

    @FXML
    private ImageView refreshIcon;

    @FXML
    private ImageView closeIcon;

    @FXML
    private HBox citiesWeatherHbox;

    @FXML
    private Label currentDate;

    @FXML
    void closeApp(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void closeIconOnMouseEntered(MouseEvent event) {
        zoomIn(closeIcon, zoomIconSize);
    }

    @FXML
    void closeIconOnMouseExited(MouseEvent event) {
        zoomOut(closeIcon, zoomIconSize);
    }

    @FXML
    void refreshIconOnMouseEntered(MouseEvent event) {
        zoomIn(refreshIcon, zoomIconSize);
    }

    @FXML
    void refreshIconOnMouseExited(MouseEvent event) {
        zoomOut(refreshIcon, zoomIconSize);
    }

    @FXML
    void refreshWeather(MouseEvent event) {

    }


    public MainWindowController2(WeatherManager weatherManager, ViewFactory viewFactory, String fxmlName) {
        super(weatherManager, viewFactory, fxmlName);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
