package pl.devtommy.model;

public class Coord {
    private double lon;
    private double lat;

    public Coord() {
    }

    public Coord(double lat, double lon) {
        this.lon = lon;
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }
}
