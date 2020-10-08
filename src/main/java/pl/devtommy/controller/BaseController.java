package pl.devtommy.controller;

import pl.devtommy.WeatherManager;
import pl.devtommy.model.WeatherProvider;
import pl.devtommy.view.ViewFactory;

public abstract class BaseController {
    protected WeatherProvider[] weathers;
    protected WeatherManager weatherManager;
    protected ViewFactory viewFactory;
    private String fxmlName;

    public BaseController(WeatherProvider[] weathers, ViewFactory viewFactory, String fxmlName) {
        this.weathers = weathers;
        this.viewFactory = viewFactory;
        this.fxmlName = fxmlName;
    }

    public BaseController(WeatherManager weatherManager) {
        this.weatherManager = weatherManager;
    }

    public String getFxmlName() {
        return fxmlName;
    }
}
