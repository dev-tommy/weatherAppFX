package pl.devtommy.view;

import pl.devtommy.WeatherManager;

public class ViewFactory {
    private WeatherManager weatherManager;

    public ViewFactory(WeatherManager weatherManager) {
        this.weatherManager = weatherManager;
    }
}
