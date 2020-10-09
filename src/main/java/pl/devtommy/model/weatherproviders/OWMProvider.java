package pl.devtommy.model.weatherproviders;

import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;
import net.aksingh.owmjapis.model.HourlyWeatherForecast;
import net.aksingh.owmjapis.model.param.WeatherData;
import org.jetbrains.annotations.NotNull;
import pl.devtommy.model.City;
import pl.devtommy.model.OneDayWeather;
import pl.devtommy.model.WeatherProvider;

import java.time.LocalDateTime;
import java.util.Date;

public class OWMProvider extends OWM implements WeatherProvider {
    private OWM owm;
    private String apiKey;
    private int MAX_FORECAST_DAYS = 5;

    public OWMProvider(@NotNull String apiKey) {
        super(apiKey);
        this.apiKey = apiKey;
        this.owm = new OWM(apiKey);
        this.owm.setUnit(OWM.Unit.METRIC);
        this.owm.setLanguage(Language.ENGLISH);
    }

    @Override
    public int getMaxForecastDays() {
        return MAX_FORECAST_DAYS;
    }

    @Override
    public OneDayWeather getCurrentWeatherByCity(City city) {
        int id = city.getId();
        String name = city.getName();
        String countryCode = city.getCountryCode();
        String countryName = city.getCountry();
        double latitude = city.getCoord().getLat();
        double longitude = city.getCoord().getLon();
        CurrentWeather currentWeather = null;

        try {
            if (id != 0) {
                currentWeather = owm.currentWeatherByCityId(id);
            }
            else if (latitude == 0 || longitude == 0) {
                currentWeather = owm.currentWeatherByCityName(name + ", "+ countryCode);
            } else {
                currentWeather = owm.currentWeatherByCoords(latitude, longitude);
            }

        } catch (Exception e) {
            System.out.println("Wrong city data!");
            e.printStackTrace();
        }
        return updateOneDayWeather(currentWeather, countryName);
    }

    @Override
    public OneDayWeather[] getForecastWeatherByCity(City city) {
        int id = city.getId();
        String name = city.getName();
        String countryCode = city.getCountryCode();
        double latitude = city.getCoord().getLat();
        double longitude = city.getCoord().getLon();
        HourlyWeatherForecast forecastWeather = null;

        try {
            if (id != 0) {
                forecastWeather = owm.hourlyWeatherForecastByCityId(id);
            }
            else if (latitude == 0 || longitude == 0) {
                forecastWeather = owm.hourlyWeatherForecastByCityName(name + ", "+ countryCode);
            } else {
                forecastWeather = owm.hourlyWeatherForecastByCoords(latitude, longitude);
            }
        } catch (Exception e) {
            System.out.println("Wrong city data!");
            e.printStackTrace();
        }
        return updateForecastWeather(forecastWeather);
    }

    private OneDayWeather updateOneDayWeather(CurrentWeather currentWeather, String countryName) {
        OneDayWeather oneDayWeather = new OneDayWeather();
        if (currentWeather.hasCityName()) oneDayWeather.setName(currentWeather.getCityName());
        if (currentWeather.hasCityId()) oneDayWeather.setId(currentWeather.getCityId());
        oneDayWeather.setCountry(countryName);
        oneDayWeather.setDescription(currentWeather.getWeatherList().get(0).getMoreInfo());
        oneDayWeather.setMainCondition(currentWeather.getWeatherList().get(0).getMainInfo());
        if (currentWeather.hasMainData()) {
            oneDayWeather.setHumidity(currentWeather.getMainData().getHumidity());
            oneDayWeather.setPressure(currentWeather.getMainData().getPressure());
            oneDayWeather.setTemp(currentWeather.getMainData().getTemp());
            oneDayWeather.setTempMin(currentWeather.getMainData().getTempMin());
            oneDayWeather.setTempMax(currentWeather.getMainData().getTempMax());
        }
        return oneDayWeather;
    }

    private OneDayWeather updateOneDayWeather(WeatherData weatherData) {
        OneDayWeather oneDayWeather = new OneDayWeather();
        oneDayWeather.setDescription(weatherData.getWeatherList().get(0).getMoreInfo());
        oneDayWeather.setMainCondition(weatherData.getWeatherList().get(0).getMainInfo());
        if (weatherData.hasMainData()) {
            oneDayWeather.setHumidity(weatherData.getMainData().getHumidity());
            oneDayWeather.setPressure(weatherData.getMainData().getPressure());
            oneDayWeather.setTemp(weatherData.getMainData().getTempMax());
            oneDayWeather.setTempMin(weatherData.getMainData().getTempMin());
            oneDayWeather.setTempMax(weatherData.getMainData().getTempMax());
        }
        return oneDayWeather;
    }

    private OneDayWeather[] updateForecastWeather(HourlyWeatherForecast hourlyWeatherForecast) {
        OneDayWeather[] fiveDaysForecastWeather = new OneDayWeather[5];
        int j = 0;
        LocalDateTime today = LocalDateTime.now();
        int currentDay = today.getDayOfMonth();

        for (int i = 0; i < hourlyWeatherForecast.getDataCount(); i++) {
            LocalDateTime hourlyWeatherDate =
                    convertToLocalDateTimeViaSqlTimestamp(hourlyWeatherForecast.getDataList().get(i).getDateTime());

            int hourOfHourlyWeather = hourlyWeatherDate.getHour();
            if ((hourlyWeatherDate.isAfter(today)) && (hourOfHourlyWeather == 14)) {
                fiveDaysForecastWeather[j++] = updateOneDayWeather(hourlyWeatherForecast.getDataList().get(i));
            }
        }
        return fiveDaysForecastWeather;
    }

    private LocalDateTime convertToLocalDateTimeViaSqlTimestamp(Date dateToConvert) {
        return new java.sql.Timestamp(
                dateToConvert.getTime()).toLocalDateTime();
    }
}
