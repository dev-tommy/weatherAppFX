package pl.devtommy.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class MainWindowController {

    @FXML
    void closeApp(MouseEvent event) {
        System.out.println("Mouse Exited");
        System.exit(0);
    }

    @FXML
    private ComboBox<?> cityComboBox;

    @FXML
    private TextArea weatherTextArea;

    @FXML
    void showButton() {

    }
}
