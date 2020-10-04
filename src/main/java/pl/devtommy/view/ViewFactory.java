package pl.devtommy.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pl.devtommy.Launcher;
import pl.devtommy.WeatherManager;
import pl.devtommy.controller.BaseController;

import java.io.IOException;

public class ViewFactory {
    private WeatherManager weatherManager;
    private Stage ownerStage;

    public ViewFactory(Stage stage, WeatherManager weatherManager) {
        this.weatherManager = weatherManager;
        this.ownerStage = stage;
    }

    private void mainStageInit(BaseController baseController) {
        FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("view/fxml/" + baseController.getFxmlName()));
        fxmlLoader.setController(baseController);

        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        ownerStage.setScene(scene);
        ownerStage.initStyle(StageStyle.TRANSPARENT);
        ownerStage.setResizable(false);
        ownerStage.show();
    }
}
