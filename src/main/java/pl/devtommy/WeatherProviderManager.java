package pl.devtommy;

import pl.devtommy.model.WeatherProvider;

public class WeatherProviderManager {
    private WeatherProvider weatherProvider;

    public WeatherProviderManager(WeatherProvider weatherProvider) {
        this.weatherProvider = weatherProvider;
    }

    private City[] generateCityList() throws FileNotFoundException {
        Gson gson = new Gson();
        City[] cityList = gson.fromJson(new FileReader("src/main/resources/pl/devtommy/json/city.list.json"),
                City[].class);
        return cityList;
    }
}
