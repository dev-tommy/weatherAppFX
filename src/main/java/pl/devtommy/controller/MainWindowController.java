package pl.devtommy.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pl.devtommy.WeatherProviderManager;
import pl.devtommy.model.City;
import pl.devtommy.model.Coord;
import pl.devtommy.model.OneDayWeather;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    private double xMainWindowOffset;
    private double yMainWindowOffset;
    WeatherProviderManager weatherProviderManager;
    City leftCity;
    City rightCity;
    OneDayWeather leftCityWeather;
    OneDayWeather rightCityWeather;
    OneDayWeather[] leftCityForecastWeather = new OneDayWeather[4];
    OneDayWeather[] rightCityForecastWeather = new OneDayWeather[4];

    public MainWindowController(WeatherProviderManager weatherProviderManager) {
        this.weatherProviderManager = weatherProviderManager;
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
        updateLeftWeatherView();
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
        updateRightWeatherView();
    }

    @FXML
    void changeLeftCity(MouseEvent event) {
        setLeftViewCityLocation();
    }

    @FXML
    void changeLeftCountry(MouseEvent event) {
        setLeftViewCityLocation();
    }

    @FXML
    void changeRightCity(MouseEvent event) {
        setRightViewCityLocation();
    }

    @FXML
    void changeRightCountry(MouseEvent event) {
        setRightViewCityLocation();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getLeftSavedCityLocation();
        getRightSavedCityLocation();
        setLeftViewCityLocation();
        setRightViewCityLocation();
        updateLeftWeatherView();
        updateRightWeatherView();
        updateDates();
        updateLeftWeatherImages();
        updateRightWeatherImages();
    }

    private void updateLeftWeatherImages() {
        String currentWeatherMainCondition = leftCityWeather.getMainCondition();
        String forecastWeatherMainCondition1 = leftCityForecastWeather[0].getMainCondition();
        String forecastWeatherMainCondition2 = leftCityForecastWeather[1].getMainCondition();
        String forecastWeatherMainCondition3 = leftCityForecastWeather[2].getMainCondition();
        String forecastWeatherMainCondition4 = leftCityForecastWeather[3].getMainCondition();

        currentLeftImageView.setImage(weatherProviderManager.getWeatherImage(currentWeatherMainCondition));
        leftWeatherImageView1.setImage(weatherProviderManager.getWeatherImage(forecastWeatherMainCondition1));
        leftWeatherImageView2.setImage(weatherProviderManager.getWeatherImage(forecastWeatherMainCondition2));
        leftWeatherImageView3.setImage(weatherProviderManager.getWeatherImage(forecastWeatherMainCondition3));
        leftWeatherImageView4.setImage(weatherProviderManager.getWeatherImage(forecastWeatherMainCondition4));
    }

    private void updateRightWeatherImages() {
        String currentWeatherMainCondition = rightCityWeather.getMainCondition();
        String forecastWeatherMainCondition1 = rightCityForecastWeather[0].getMainCondition();
        String forecastWeatherMainCondition2 = rightCityForecastWeather[1].getMainCondition();
        String forecastWeatherMainCondition3 = rightCityForecastWeather[2].getMainCondition();
        String forecastWeatherMainCondition4 = rightCityForecastWeather[3].getMainCondition();

        currentRightImageView.setImage(weatherProviderManager.getWeatherImage(currentWeatherMainCondition));
        rightWeatherImageView1.setImage(weatherProviderManager.getWeatherImage(forecastWeatherMainCondition1));
        rightWeatherImageView2.setImage(weatherProviderManager.getWeatherImage(forecastWeatherMainCondition2));
        rightWeatherImageView3.setImage(weatherProviderManager.getWeatherImage(forecastWeatherMainCondition3));
        rightWeatherImageView4.setImage(weatherProviderManager.getWeatherImage(forecastWeatherMainCondition4));
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
        weatherProviderManager.setLeftCity(leftCity);
    }

    private void setRightViewCityLocation() {
        weatherProviderManager.setRightCity(rightCity);
    }

    private void getRightSavedCityLocation() {
    }

    private void getRightSavedCityLocation() {
        rightCity = new City(0, "Zakynthos", "", "", new Coord(0.0, 0.0));
    }

    private void updateLeftWeatherView() {
        leftCityWeather = weatherProviderManager.getCurrentLeftCityWeather();
        leftCityForecastWeather = weatherProviderManager.getForecastLeftCityWeather();

        System.out.println(leftCityWeather);
        System.out.println(Arrays.toString(leftCityForecastWeather));

        leftCityLabel.setText(leftCityWeather.getName());
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
        rightCityWeather = weatherProviderManager.getCurrentRightCityWeather();
        rightCityForecastWeather = weatherProviderManager.getForecastRightCityWeather();

        System.out.println(rightCityWeather);
        System.out.println(Arrays.toString(rightCityForecastWeather));

        rightCityLabel.setText(rightCityWeather.getName());
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




}


