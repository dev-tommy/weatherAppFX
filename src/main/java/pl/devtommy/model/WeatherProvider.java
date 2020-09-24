package pl.devtommy.model;

public interface WeatherProvider {
    public OneDayWeather getCurrentWeatherByCity(City city);
}
