package pl.devtommy;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.devtommy.model.Config;
import pl.devtommy.model.Paths;
import pl.devtommy.model.WeatherProvider;
import pl.devtommy.model.weatherproviders.OWMProvider;
import pl.devtommy.view.ViewFactory;

public class Launcher extends Application {
    public static Config config = new Config(Paths.CONFIG_PROPERTIES_PATH);

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        ViewFactory viewFactory = new ViewFactory(stage, loadWeatherProvider(), config);
        viewFactory.showMainWindow();
    }

    private WeatherProvider loadWeatherProvider() {
        return new OWMProvider(config.getApiKey());
    }


}