package pl.devtommy.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainWindowController {

    private double xMainWindowOffset;
    private double yMainWindowOffset;

    @FXML
    private ImageView currentLeftImageView;

    @FXML
    private ImageView refreshLeftImageView;

    @FXML
    private Label currentLeftTempLabel;

    @FXML
    private Label leftDescribeLabel;

    @FXML
    private Label leftCountryLabel;

    @FXML
    private Label leftCityLabel;

    @FXML
    private Label leftMonthLabel;

    @FXML
    private Label leftDayLabel;

    @FXML
    private ImageView leftCityImageView;

    @FXML
    private ImageView leftCountryImageView;

    @FXML
    private ImageView closeAppImageView;

    @FXML
    private ImageView refreshRightImageView;

    @FXML
    private ImageView currentRightImageView;

    @FXML
    private Label currentRightTempLabel;

    @FXML
    private Label rightMonthLabel;

    @FXML
    private Label rightDayLabel;

    @FXML
    private Label rightDescribeLabel;

    @FXML
    private Label rightCountryLabel;

    @FXML
    private Label rightCityLabel;

    @FXML
    private ImageView rightCityImageView;

    @FXML
    private ImageView rightCountryImageView;

    @FXML
    void closeAppOnMouseEntered(MouseEvent event) {
        zoomIn(closeAppImageView, 10);
    }

    @FXML
    void closeAppOnMouseExited(MouseEvent event) {
        zoomOut(closeAppImageView, 10);
    }

    @FXML
    void closeApp(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void refreshLeftOnMouseEntered(MouseEvent event) {
        zoomIn(refreshLeftImageView, 10);
    }

    @FXML
    void refreshLeftOnMouseExited(MouseEvent event) {
        zoomOut(refreshLeftImageView, 10);
    }

    @FXML
    void refreshLeftWeather(MouseEvent event) {
    }

    @FXML
    void refreshRightOnMouseEntered(MouseEvent event) {
        zoomIn(refreshRightImageView, 10);
    }

    @FXML
    void refreshRightOnMouseExited(MouseEvent event) {
        zoomOut(refreshRightImageView, 10);
    }

    @FXML
    void refreshRightWeather(MouseEvent event) {
    }

    @FXML
    void changeLeftCity(MouseEvent event) {

    }

    @FXML
    void changeLeftCountry(MouseEvent event) {

    }

    @FXML
    void changeRightCity(MouseEvent event) {

    }

    @FXML
    void changeRightCountry(MouseEvent event) {

    }

    @FXML
    void leftCityOnMouseEntered(MouseEvent event) {
        zoomIn(leftCityImageView, 10);
    }

    @FXML
    void leftCityOnMouseExited(MouseEvent event) {
        zoomOut(leftCityImageView, 10);
    }

    @FXML
    void leftCountryOnMouseEntered(MouseEvent event) {
        zoomIn(leftCountryImageView, 10);
    }

    @FXML
    void leftCountryOnMouseExited(MouseEvent event) {
        zoomOut(leftCountryImageView, 10);
    }

    @FXML
    void rightCityOnMouseEntered(MouseEvent event) {
        zoomIn(rightCityImageView, 10);
    }

    @FXML
    void rightCityOnMouseExited(MouseEvent event) {
        zoomOut(rightCityImageView, 10);
    }

    @FXML
    void rightCountryOnMouseEntered(MouseEvent event) {
        zoomIn(rightCountryImageView, 10);
    }

    @FXML
    void rightCountryOnMouseExited(MouseEvent event) {
        zoomOut(rightCountryImageView, 10);
    }

    @FXML
    void takeMouseOffsetOnMousePressed(MouseEvent event) {
        Stage stage = (Stage) closeAppImageView.getScene().getWindow();
        xMainWindowOffset = stage.getX() - event.getScreenX();
        yMainWindowOffset = stage.getY() - event.getScreenY();
    }

    @FXML
    void moveMainWindowOnMouseDragged(MouseEvent event) {
        Stage stage = (Stage) closeAppImageView.getScene().getWindow();
        stage.setX(event.getScreenX() + xMainWindowOffset);
        stage.setY(event.getScreenY() + yMainWindowOffset);
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


