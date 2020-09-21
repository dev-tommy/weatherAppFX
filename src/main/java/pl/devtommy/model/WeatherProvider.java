package pl.devtommy.model;

public interface WeatherProvider {
    public OneDayWeather getCurrentWeatherByCityName(String cityName);
    public OneDayWeather getCurrentWeatherByCityId(int cityId);
}
