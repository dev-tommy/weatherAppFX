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
    private int id;
    private String name;
    private OWM owm;
    private String apiKey;

    public OWMProvider(@NotNull String apiKey) {
        super(apiKey);
        this.apiKey = apiKey;
        this.owm = new OWM(apiKey);
        this.owm.setUnit(OWM.Unit.METRIC);
        this.owm.setLanguage(OWM.Language.POLISH);
    }

    @Override
    public OneDayWeather getCurrentWeatherByCity(City city) {
        int id = city.getId();
        String name = city.getName();
        String country = city.getCountry();
        double latitude = city.getCoord().getLat();
        double longitude = city.getCoord().getLon();
        CurrentWeather currentWeather = null;

        try {
            if (id != 0) {
                currentWeather = owm.currentWeatherByCityId(id);
            }
            else if (latitude == 0 || longitude == 0) {
                currentWeather = owm.currentWeatherByCityName(name + ", "+ country);
            } else {
                currentWeather = owm.currentWeatherByCoords(latitude, longitude);
            }

        } catch (Exception e) {
            System.out.println("Wrong city data!");
            e.printStackTrace();
        }
        return updateOneDayWeather(currentWeather);
    }

    @Override
    public OneDayWeather[] getForecastWeatherByCity(City city) {
        int id = city.getId();
        String name = city.getName();
        String country = city.getCountry();
        double latitude = city.getCoord().getLat();
        double longitude = city.getCoord().getLon();
        HourlyWeatherForecast forecastWeather = null;

        try {
            if (id != 0) {
                forecastWeather = owm.hourlyWeatherForecastByCityId(id);
            }
            else if (latitude == 0 || longitude == 0) {
                forecastWeather = owm.hourlyWeatherForecastByCityName(name + ", "+ country);
            } else {
                forecastWeather = owm.hourlyWeatherForecastByCoords(latitude, longitude);
            }
        } catch (Exception e) {
            System.out.println("Wrong city data!");
            e.printStackTrace();
        }
        return updateForecastWeather(forecastWeather);
    }

    private OneDayWeather updateOneDayWeather(CurrentWeather cwd) {
        OneDayWeather oneDayWeather = new OneDayWeather();
        if (cwd.hasCityName()) oneDayWeather.setName(cwd.getCityName());
        if (cwd.hasCityId()) oneDayWeather.setId(cwd.getCityId());
        if (cwd.hasMainData()) {
            oneDayWeather.setHumidity(cwd.getMainData().getHumidity());
            oneDayWeather.setPressure(cwd.getMainData().getPressure());
            oneDayWeather.setTemp(cwd.getMainData().getTemp());
            oneDayWeather.setTempMin(cwd.getMainData().getTempMin());
            oneDayWeather.setTempMax(cwd.getMainData().getTempMax());
        }
        return oneDayWeather;
    }

    private OneDayWeather updateOneDayWeather(WeatherData wd) {
        OneDayWeather oneDayWeather = new OneDayWeather();
        if (wd.hasMainData()) {
            oneDayWeather.setHumidity(wd.getMainData().getHumidity());
            oneDayWeather.setPressure(wd.getMainData().getPressure());
            oneDayWeather.setTemp(wd.getMainData().getTemp());
        }
        return oneDayWeather;
    }

    private OneDayWeather[] updateForecastWeather(HourlyWeatherForecast hourlyWeatherForecast) {
        OneDayWeather[] fiveDaysForecastWeather = new OneDayWeather[4];
        int j = 0;
        LocalDateTime today = LocalDateTime.now();
        int currentDay = today.getDayOfMonth();

        for (int i = 0; i < hourlyWeatherForecast.getDataCount(); i++) {
            LocalDateTime hourlyWeatherDate =
                    convertToLocalDateTimeViaSqlTimestamp(hourlyWeatherForecast.getDataList().get(i).getDateTime());
            int dayOfMonth = hourlyWeatherDate.getDayOfMonth();
            int hourOfHourlyWeather = hourlyWeatherDate.getHour();
            if ((dayOfMonth > currentDay) && (hourOfHourlyWeather == 11)) {
                fiveDaysForecastWeather[j++] = updateOneDayWeather(hourlyWeatherForecast.getDataList().get(i));
            }
        }
        return fiveDaysForecastWeather;
    }

    public LocalDateTime convertToLocalDateTimeViaSqlTimestamp(Date dateToConvert) {
        return new java.sql.Timestamp(
                dateToConvert.getTime()).toLocalDateTime();
    }
}
