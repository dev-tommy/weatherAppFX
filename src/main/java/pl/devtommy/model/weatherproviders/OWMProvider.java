package pl.devtommy.model.weatherproviders;

import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;
import org.jetbrains.annotations.NotNull;
import pl.devtommy.model.OneDayWeather;
import pl.devtommy.model.WeatherProvider;

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
    public OneDayWeather getCurrentWeatherByCityName(String cityName) {
        OneDayWeather oneDayWeather = new OneDayWeather();
        try {
            CurrentWeather cwd = owm.currentWeatherByCityName(cityName);
            updateOneDayWeather(oneDayWeather, cwd);
        } catch (Exception e) {
            System.out.println("Wrong city name!");
            e.printStackTrace();
        }
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

    @Override
    public OneDayWeather getCurrentWeatherByCityId(int cityId) {
        OneDayWeather oneDayWeather = new OneDayWeather();
        try {
            CurrentWeather cwd = owm.currentWeatherByCityId(cityId);
            updateOneDayWeather(oneDayWeather, cwd);
        } catch (Exception e) {
            System.out.println("Wrong city id!");
            //e.printStackTrace();
        }
        return oneDayWeather;
    }

}
