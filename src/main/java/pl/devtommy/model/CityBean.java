package pl.devtommy.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.Initializable;

public class CityBean {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty country;
    private SimpleDoubleProperty latitude;
    private SimpleDoubleProperty longitude;

    public CityBean(int id, String name, String country, double latitude, double longitude) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.country = new SimpleStringProperty(country);
        this.latitude = new SimpleDoubleProperty(latitude);
        this.longitude = new SimpleDoubleProperty(longitude);
    }

    public Integer getId(){
        return this.id.get();
    }

    public String getName(){
        return this.name.get();
    }

    public String getCountry(){
        return this.country.get();
    }

    public Double getLatitude(){
        return this.latitude.get();
    }

    public Double getLongitude(){
        return this.longitude.get();
    }


}
