package pl.devtommy.controller;

import pl.devtommy.WeatherManager;
import pl.devtommy.view.ViewFactory;

public abstract class BaseController {
    protected WeatherManager weatherManager;
    protected ViewFactory viewFactory;
    private String fxmlName;

    public BaseController(WeatherManager weatherManager, ViewFactory viewFactory, String fxmlName) {
        this.weatherManager = weatherManager;
        this.viewFactory = viewFactory;
        this.fxmlName = fxmlName;
    }
}
