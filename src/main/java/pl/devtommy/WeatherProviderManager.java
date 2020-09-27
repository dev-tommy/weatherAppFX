package pl.devtommy;

import pl.devtommy.model.WeatherProvider;

public class WeatherProviderManager {
    private WeatherProvider weatherProvider;

    public WeatherProviderManager(WeatherProvider weatherProvider) {
        this.weatherProvider = weatherProvider;
    }
}
