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
import java.util.Arrays;
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
        OneDayWeather oneDayWeather = new OneDayWeather();
        int id = city.getId();
        String name = city.getName();
        String country = city.getCountry();
        Double latitude = city.getCoord().getLat();
        Double longitude = city.getCoord().getLon();
        CurrentWeather currentWeather = null;

        try {
            if (id != 0) {
                currentWeather = owm.currentWeatherByCityId(7533329);
                updateOneDayWeather(oneDayWeather, currentWeather);
            }
            else if (latitude == 0 || longitude == 0) {
                currentWeather = owm.currentWeatherByCityName(name + ", "+ country);
                updateOneDayWeather(oneDayWeather, currentWeather);
            } else {
                currentWeather = owm.currentWeatherByCoords(latitude, longitude);

            }

        } catch (Exception e) {
            System.out.println("Wrong city data!");
            //e.printStackTrace();
        }
        updateOneDayWeather(oneDayWeather, currentWeather);
        return oneDayWeather;
    }

    private void updateOneDayWeather(OneDayWeather oneDayWeather, CurrentWeather cwd) {
        if (cwd.hasCityName()) oneDayWeather.setName(cwd.getCityName());
        if (cwd.hasCityId()) oneDayWeather.setId(cwd.getCityId());
        if (cwd.hasMainData()) {
            oneDayWeather.setHumidity(cwd.getMainData().getHumidity());
            oneDayWeather.setPressure(cwd.getMainData().getPressure());
            oneDayWeather.setTemp(cwd.getMainData().getTemp());
            oneDayWeather.setTempMin(cwd.getMainData().getTempMin());
            oneDayWeather.setTempMax(cwd.getMainData().getTempMax());
        }
    }
}
