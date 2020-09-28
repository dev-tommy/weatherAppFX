package pl.devtommy.model;

public interface WeatherProvider {
    public OneDayWeather getCurrentWeatherByCity(City city);
    public OneDayWeather[] getForecastWeatherByCity(City city);
}
