package pl.devtommy.model;

public class City {
    private int id;
    private String name;
    private String state;
    private String country;
    private Coord coord;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public Coord getCoord() {
        return coord;
    }
}
