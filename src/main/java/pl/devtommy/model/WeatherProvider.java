package pl.devtommy.model;

public interface WeatherProvider {
    DayWeather getCurrentWeatherByCity(City city);
    DayWeather[] getForecastWeatherByCity(City city);
    int getMaxForecastDays();
}
