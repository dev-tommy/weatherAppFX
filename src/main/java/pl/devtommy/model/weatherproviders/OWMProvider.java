package pl.devtommy.model.weatherproviders;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;
import net.aksingh.owmjapis.model.HourlyWeatherForecast;
import net.aksingh.owmjapis.model.param.WeatherData;
import org.jetbrains.annotations.NotNull;
import pl.devtommy.model.*;
import pl.devtommy.view.ViewFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

public class OWMProvider implements WeatherProvider {
    private OWM owm;
    private static final int MAX_FORECAST_DAYS = 4;

    public OWMProvider(@NotNull String apiKey) {
        while ( !isCorrectApiKey(apiKey) ) {
            System.err.println(Messages.WRONG_API_KEY_MESSAGE);
            apiKey = ViewFactory.getApiDialog();
        }
        Config.setApiKey(apiKey);
        Config.store();
        this.owm = new OWM(apiKey);
        this.owm.setUnit(OWM.Unit.METRIC);
        this.owm.setLanguage(OWM.Language.ENGLISH);
    }

    private boolean isCorrectApiKey(String apiKey) {
        OWM owm = new OWM(apiKey);
        try {
            owm.currentWeatherByCityName("Rome", OWM.Country.ITALY);
            return true;
        } catch (APIException e) {
            return false;
        }
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
            System.err.println(Messages.WRONG_CITY_DATA_MESSAGE);
        }
        return createDayWeather(currentWeather);
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
        } catch (APIException e) {
            System.err.println("Wrong city data!");
        }
        return createForecastWeather(forecastWeather);
    }

    private DayWeather createDayWeather(CurrentWeather currentWeather) {
        String countryName =
                new Locale("", currentWeather.getSystemData().getCountryCode())
                        .getDisplayCountry(new Locale("EN"));
        DayWeather dayWeather = new DayWeather();
        if (currentWeather.hasCityName()) {
            dayWeather.setName(currentWeather.getCityName());
        }
        if (currentWeather.hasCityId()) {
            dayWeather.setId(currentWeather.getCityId());
        }
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

    private DayWeather createDayWeather(WeatherData weatherData) {
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

    private DayWeather[] createForecastWeather(HourlyWeatherForecast hourlyWeatherForecast) {
        int hourOfWeatherToShow = 13;
        DayWeather[] fiveDaysForecastWeather = new DayWeather[5];
        LocalDateTime today = LocalDate.now().atTime(23,59,59);

        int j = 0;
        for (int i = 0; i < hourlyWeatherForecast.getDataCount(); i++) {
            LocalDateTime hourlyWeatherDate =
                    convertDateToLocalDateTime(hourlyWeatherForecast.getDataList().get(i).getDateTime());
            int hourOfHourlyWeather = hourlyWeatherDate.getHour();
            if ((hourlyWeatherDate.isAfter(today)) && (hourOfHourlyWeather == hourOfWeatherToShow || hourOfHourlyWeather == (hourOfWeatherToShow+1))) {
                fiveDaysForecastWeather[j++] = createDayWeather(hourlyWeatherForecast.getDataList().get(i));
            }
        }
        return fiveDaysForecastWeather;
    }

    private LocalDateTime convertDateToLocalDateTime(Date dateToConvert) {
        return LocalDateTime.ofInstant(
                dateToConvert.toInstant(), ZoneId.systemDefault());
    }
}
