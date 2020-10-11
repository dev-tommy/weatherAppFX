package pl.devtommy.model;

public interface WeatherProvider {
    public DayWeather getCurrentWeatherByCity(City city);
    public DayWeather[] getForecastWeatherByCity(City city);
    public int getMaxForecastDays();
}
