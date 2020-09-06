module pl.devtommy {
    requires javafx.controls;
    requires javafx.fxml;

    opens pl.devtommy to javafx.fxml;
    exports pl.devtommy;
}