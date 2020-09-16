module pl.devtommy {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires owm.japis;
    requires java.sql;

    opens pl.devtommy;
    opens pl.devtommy.controller;
    opens pl.devtommy.model;
}