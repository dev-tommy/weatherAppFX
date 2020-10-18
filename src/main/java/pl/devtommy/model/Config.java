package pl.devtommy.model;

import pl.devtommy.view.ViewFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static String configPath;

    public void setPath(String configPath) {
        this.configPath = configPath;
    }

    public String getConfigPath() {
        return configPath;
    }

    public String getApiKey() {
        String apiKey = "";
        try (InputStream input = new FileInputStream(Paths.CONFIG_PROPERTIES_PATH)) {
            Properties prop = new Properties();
            prop.load(input);

            apiKey = prop.getProperty("api.key");

        } catch (IOException ex) {
            System.err.println("OWM API key not found!");
            while(apiKey.length() != 32) {
                System.err.println("API key must be 32 characters long!");
                apiKey = ViewFactory.getApiDialog();
                if (apiKey.isEmpty()) {
                    try {
                        Properties prop = new Properties();
                        prop.load(new FileInputStream(ViewFactory.getFileDialog()));
                        apiKey = prop.getProperty("api.key");
                    } catch (Exception e) {
                        System.err.println("File error");
                        System.exit(1);
                    }
                    break;
                }
            }
        }

        return apiKey;
    }
}
