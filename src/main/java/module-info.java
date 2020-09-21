module pl.devtommy {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires owm.japis;
    requires java.sql;
    requires annotations;
    requires gson;

    opens pl.devtommy;
    opens pl.devtommy.controller;
    opens pl.devtommy.model;
}