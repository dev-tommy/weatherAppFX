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
import pl.devtommy.controller.MainWindowController;

import java.io.IOException;

public class ViewFactory {
    private WeatherManager weatherManager;
    private Stage ownerStage;

    public ViewFactory(Stage stage, WeatherManager weatherManager) {
        this.weatherManager = weatherManager;
        this.ownerStage = stage;
    }

    public void showMainWindow() {
        BaseController controller = new MainWindowController(weatherManager, this, "MainWindow.fxml");
        mainStageInit(controller);
    }

    private void mainStageInit(BaseController baseController) {
        Parent parent;

        try {
            parent = loadFxml(baseController).load();
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

    private FXMLLoader loadFxml(BaseController baseController) {
        FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("view/fxml/" + baseController.getFxmlName()));
        fxmlLoader.setController(baseController);
        return fxmlLoader;
    }
}
