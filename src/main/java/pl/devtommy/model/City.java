package pl.devtommy.model;

import java.util.Locale;
import java.util.Objects;

public class City {
    private int id;
    private String name;
    private String country;
    private Coord coord;

    public City() {
        //for Gson
    }

    public City(int id, String name, String country, Coord coord) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.coord = coord;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountryCode() {
        return country;
    }

    public String getCountry() {
        return new Locale("", getCountryCode()).getDisplayCountry(new Locale("EN"));
    }

    public Coord  getCoord() {
        return coord;
    }

    // for SelectCityLocationController.setUpTableView()
    public Double getLatitude() {
        return coord.getLat();
    }

    // for SelectCityLocationController.setUpTableView()
    public Double getLongitude() {
        return coord.getLon();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return id == city.id &&
                name.equals(city.name) &&
                country.equals(city.country) &&
                coord.equals(city.coord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country, coord);
    }

    @Override
    public String toString() {
        String answer = "";

        answer += "ID: "+ getId() +"\n";
        answer += "Name: "+ getName() +"\n";
        answer += "Country code: "+ getCountryCode() +"\n";
        answer += "Country name: "+ getCountry() +"\n";
        answer += "Coord: Lon: "+ coord.getLon() +"\n";
        answer += "Coord: Lat: "+ coord.getLat() +"\n";
        answer += "-------------------------------- \n";
        return answer;
    }
}
