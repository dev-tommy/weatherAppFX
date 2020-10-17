package pl.devtommy.model.weatherproviders;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;
import net.aksingh.owmjapis.model.HourlyWeatherForecast;
import net.aksingh.owmjapis.model.param.WeatherData;
import org.jetbrains.annotations.NotNull;
import pl.devtommy.model.City;
import pl.devtommy.model.DayWeather;
import pl.devtommy.model.WeatherProvider;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class OWMProvider implements WeatherProvider {
    private OWM owm;
    private static final int MAX_FORECAST_DAYS = 4;

    public OWMProvider(@NotNull String apiKey) {
        this.owm = new OWM(apiKey);
        validateApiKey();

        this.owm.setUnit(OWM.Unit.METRIC);
        this.owm.setLanguage(OWM.Language.ENGLISH);
    }

    @Override
    public int getMaxForecastDays() {
        return MAX_FORECAST_DAYS;
    }

    @Override
    public DayWeather getCurrentWeatherByCity(City city) {
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
    public DayWeather[] getForecastWeatherByCity(City city) {
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

    private void validateApiKey() {
        try {
            owm.currentWeatherByCityName("Rome", OWM.Country.ITALY);
        } catch (APIException e) {
            System.out.println("Wrong API key! API call gave error: 401 - Unauthorized");
            System.exit(401);
        }
    }

    private DayWeather updateOneDayWeather(CurrentWeather currentWeather, String countryName) {
        DayWeather dayWeather = new DayWeather();
        if (currentWeather.hasCityName()) dayWeather.setName(currentWeather.getCityName());
        if (currentWeather.hasCityId()) dayWeather.setId(currentWeather.getCityId());
        dayWeather.setDate(currentWeather.getDateTime());
        dayWeather.setCountry(countryName);
        dayWeather.setDescription(currentWeather.getWeatherList().get(0).getMoreInfo());
        dayWeather.setMainCondition(currentWeather.getWeatherList().get(0).getMainInfo());
        if (currentWeather.hasMainData()) {
            dayWeather.setHumidity(currentWeather.getMainData().getHumidity());
            dayWeather.setPressure(currentWeather.getMainData().getPressure());
            dayWeather.setTemp(currentWeather.getMainData().getTemp());
            dayWeather.setTempMin(currentWeather.getMainData().getTempMin());
            dayWeather.setTempMax(currentWeather.getMainData().getTempMax());
        }
        return dayWeather;
    }

    private DayWeather updateOneDayWeather(WeatherData weatherData) {
        DayWeather dayWeather = new DayWeather();
        dayWeather.setDescription(weatherData.getWeatherList().get(0).getMoreInfo());
        dayWeather.setMainCondition(weatherData.getWeatherList().get(0).getMainInfo());
        dayWeather.setDate(weatherData.getDateTime());
        if (weatherData.hasMainData()) {
            dayWeather.setHumidity(weatherData.getMainData().getHumidity());
            dayWeather.setPressure(weatherData.getMainData().getPressure());
            dayWeather.setTemp(weatherData.getMainData().getTempMax());
            dayWeather.setTempMin(weatherData.getMainData().getTempMin());
            dayWeather.setTempMax(weatherData.getMainData().getTempMax());
        }
        return dayWeather;
    }

    private DayWeather[] updateForecastWeather(HourlyWeatherForecast hourlyWeatherForecast) {
        DayWeather[] fiveDaysForecastWeather = new DayWeather[5];
        int j = 0;
        LocalDateTime today = LocalDate.now().atTime(23,59,59);

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
