package pl.devtommy.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pl.devtommy.WeatherManager;
import pl.devtommy.model.City;
import pl.devtommy.model.OneDayWeather;
import pl.devtommy.view.ViewFactory;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.ResourceBundle;

public class MainWindowControllerOLD extends BaseController implements Initializable {

    private double xMainWindowOffset;
    private double yMainWindowOffset;
    private City leftCity;
    private City rightCity;
    private OneDayWeather leftCityWeather;
    private OneDayWeather rightCityWeather;
    private OneDayWeather[] leftCityForecastWeather = new OneDayWeather[4];
    private OneDayWeather[] rightCityForecastWeather = new OneDayWeather[4];

    public MainWindowControllerOLD(WeatherManager weatherManager, ViewFactory viewFactory, String fxmlName) {
        super(weatherManager, viewFactory, fxmlName);
    }

    @FXML
    private Label leftTempLabel1;

    @FXML
    private Label leftTempLabel2;

    @FXML
    private Label leftTempLabel3;

    @FXML
    private Label leftTempLabel4;

    @FXML
    private Label rightTempLabel1;

    @FXML
    private Label rightTempLabel2;

    @FXML
    private Label rightTempLabel3;

    @FXML
    private Label rightTempLabel4;

    @FXML
    private Label leftHumidityLabel;

    @FXML
    private Label leftPressureLabel;

    @FXML
    private Label leftTempMinMaxLabel;

    @FXML
    private Label rightHumidityLabel;

    @FXML
    private Label rightPressureLabel;

    @FXML
    private Label rightTempMinMaxLabel;

    @FXML
    private Label leftDayOfMonthLabel1;

    @FXML
    private Label leftDayOfMonthLabel2;

    @FXML
    private Label leftDayOfMonthLabel3;

    @FXML
    private Label leftDayOfMonthLabel4;

    @FXML
    private Label rightDayOfMonthLabel1;

    @FXML
    private Label rightDayOfMonthLabel2;

    @FXML
    private Label rightDayOfMonthLabel3;

    @FXML
    private Label rightDayOfMonthLabel4;

    @FXML
    private ImageView currentLeftImageView;

    @FXML
    private ImageView refreshLeftImageView;

    @FXML
    private ImageView leftWeatherImageView1;

    @FXML
    private ImageView leftWeatherImageView2;

    @FXML
    private ImageView leftWeatherImageView3;

    @FXML
    private ImageView leftWeatherImageView4;

    @FXML
    private ImageView rightWeatherImageView1;

    @FXML
    private ImageView rightWeatherImageView2;

    @FXML
    private ImageView rightWeatherImageView3;

    @FXML
    private ImageView rightWeatherImageView4;


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
    private ImageView rightCityImageView;

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
    void refreshLeftWeather() {
        setLeftViewCityLocation();
        updateLeftWeatherView();
        updateLeftWeatherImages();
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
    void refreshRightWeather() {
        setRightViewCityLocation();
        updateRightWeatherView();;
        updateRightWeatherImages();
    }

    @FXML
    void changeLeftCity() {
        getLeftCity();
    }

    @FXML
    void changeRightCity() {
        getRightCity();
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
    void rightCityOnMouseEntered(MouseEvent event) {
        zoomIn(rightCityImageView, 10);
    }

    @FXML
    void rightCityOnMouseExited(MouseEvent event) {
        zoomOut(rightCityImageView, 10);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getLeftSavedCityLocation();
        getRightSavedCityLocation();
        setLeftViewCityLocation();
        setRightViewCityLocation();
        refreshLeftWeather();
        refreshRightWeather();
        updateDates();
    }

    private void updateLeftWeatherImages() {
        String currentWeatherMainCondition = leftCityWeather.getMainCondition();
        String forecastWeatherMainCondition1 = leftCityForecastWeather[0].getMainCondition();
        String forecastWeatherMainCondition2 = leftCityForecastWeather[1].getMainCondition();
        String forecastWeatherMainCondition3 = leftCityForecastWeather[2].getMainCondition();
        String forecastWeatherMainCondition4 = leftCityForecastWeather[3].getMainCondition();

        currentLeftImageView.setImage(weatherManager.getWeatherImage(currentWeatherMainCondition));
        leftWeatherImageView1.setImage(weatherManager.getWeatherImage(forecastWeatherMainCondition1));
        leftWeatherImageView2.setImage(weatherManager.getWeatherImage(forecastWeatherMainCondition2));
        leftWeatherImageView3.setImage(weatherManager.getWeatherImage(forecastWeatherMainCondition3));
        leftWeatherImageView4.setImage(weatherManager.getWeatherImage(forecastWeatherMainCondition4));
    }

    private void updateRightWeatherImages() {
        String currentWeatherMainCondition = rightCityWeather.getMainCondition();
        String forecastWeatherMainCondition1 = rightCityForecastWeather[0].getMainCondition();
        String forecastWeatherMainCondition2 = rightCityForecastWeather[1].getMainCondition();
        String forecastWeatherMainCondition3 = rightCityForecastWeather[2].getMainCondition();
        String forecastWeatherMainCondition4 = rightCityForecastWeather[3].getMainCondition();

        currentRightImageView.setImage(weatherManager.getWeatherImage(currentWeatherMainCondition));
        rightWeatherImageView1.setImage(weatherManager.getWeatherImage(forecastWeatherMainCondition1));
        rightWeatherImageView2.setImage(weatherManager.getWeatherImage(forecastWeatherMainCondition2));
        rightWeatherImageView3.setImage(weatherManager.getWeatherImage(forecastWeatherMainCondition3));
        rightWeatherImageView4.setImage(weatherManager.getWeatherImage(forecastWeatherMainCondition4));
    }

    private void updateDates() {
        LocalDateTime today = LocalDateTime.now();
        int day = today.getDayOfMonth();
        int nextday1 = today.plusDays(1).getDayOfMonth();
        int nextday2 = today.plusDays(2).getDayOfMonth();
        int nextday3 = today.plusDays(3).getDayOfMonth();
        int nextday4 = today.plusDays(4).getDayOfMonth();
        Month month = today.getMonth();
        DayOfWeek dayOfWeek = today.getDayOfWeek();

        leftMonthLabel.setText(month.name().substring(0,1).toUpperCase() + month.name().substring(1,3).toLowerCase());
        leftDayLabel.setText(dayOfWeek.name().substring(0,1).toUpperCase() + dayOfWeek.name().substring(1,3).toLowerCase() + "," +
                " " + day);

        rightMonthLabel.setText(month.name().substring(0,1).toUpperCase() + month.name().substring(1,3).toLowerCase());
        rightDayLabel.setText(dayOfWeek.name().substring(0,1).toUpperCase() + dayOfWeek.name().substring(1,3).toLowerCase() + "," +
                " " + day);

        leftDayOfMonthLabel1.setText(String.valueOf(nextday1));
        leftDayOfMonthLabel2.setText(String.valueOf(nextday2));
        leftDayOfMonthLabel3.setText(String.valueOf(nextday3));
        leftDayOfMonthLabel4.setText(String.valueOf(nextday4));

        rightDayOfMonthLabel1.setText(String.valueOf(nextday1));
        rightDayOfMonthLabel2.setText(String.valueOf(nextday2));
        rightDayOfMonthLabel3.setText(String.valueOf(nextday3));
        rightDayOfMonthLabel4.setText(String.valueOf(nextday4));
    }

    private void setLeftViewCityLocation() {
        weatherManager.setLeftCity(leftCity);
    }

    private void setRightViewCityLocation() {
        weatherManager.setRightCity(rightCity);
    }

    private void getLeftSavedCityLocation() {
        leftCity = weatherManager.getLeftSavedCityLocation();
    }

    private void getRightSavedCityLocation() {
        rightCity = weatherManager.getRightSavedCityLocation();
    }

    private void updateLeftWeatherView() {
        leftCityWeather = weatherManager.getCurrentLeftCityWeather();
        leftCityForecastWeather = weatherManager.getForecastLeftCityWeather();

        System.out.println(leftCityWeather);
        System.out.println(Arrays.toString(leftCityForecastWeather));

        leftCityLabel.setText(leftCityWeather.getName());
        leftCountryLabel.setText(leftCityWeather.getCountry());
        currentLeftTempLabel.setText( leftCityWeather.getTemp());
        leftHumidityLabel.setText(leftCityWeather.getHumidity());
        leftPressureLabel.setText(leftCityWeather.getPressure());
        leftTempMinMaxLabel.setText(
                leftCityWeather.getTempMin() +
                " / " +
                leftCityWeather.getTempMax()
        );

        leftTempLabel1.setText( leftCityForecastWeather[0].getTemp() );
        leftTempLabel2.setText( leftCityForecastWeather[1].getTemp() );
        leftTempLabel3.setText( leftCityForecastWeather[2].getTemp() );
        leftTempLabel4.setText( leftCityForecastWeather[3].getTemp() );

        leftDescribeLabel.setText(leftCityWeather.getDescription());
    }

    private void updateRightWeatherView() {
        rightCityWeather = weatherManager.getCurrentRightCityWeather();
        rightCityForecastWeather = weatherManager.getForecastRightCityWeather();

        System.out.println(rightCityWeather);
        System.out.println(Arrays.toString(rightCityForecastWeather));

        rightCityLabel.setText(rightCityWeather.getName());
        rightCountryLabel.setText(rightCityWeather.getCountry());
        currentRightTempLabel.setText( rightCityWeather.getTemp());
        rightHumidityLabel.setText(rightCityWeather.getHumidity());
        rightPressureLabel.setText(rightCityWeather.getPressure());
        rightTempMinMaxLabel.setText(
                rightCityWeather.getTempMin() +
                        " / " +
                        rightCityWeather.getTempMax()
        );

        rightTempLabel1.setText( rightCityForecastWeather[0].getTemp() );
        rightTempLabel2.setText( rightCityForecastWeather[1].getTemp() );
        rightTempLabel3.setText( rightCityForecastWeather[2].getTemp() );
        rightTempLabel4.setText( rightCityForecastWeather[3].getTemp() );

        rightDescribeLabel.setText(rightCityWeather.getDescription());
    }

    private void getLeftCity() {
        weatherManager.setSelectedCity(null);
        viewFactory.showSelectCityLocationWindow();
        leftCity = weatherManager.getSelectedCity();
        refreshLeftWeather();
    }

    private void getRightCity() {
        weatherManager.setSelectedCity(null);
        viewFactory.showSelectCityLocationWindow();
        rightCity = weatherManager.getSelectedCity();
        refreshRightWeather();
    }


}


