package pl.devtommy.model;

public class City {
    private int id;
    private String name;
    private String state;
    private String country;
    private Coord coord;

    public City() {
    }

    public City(int id, String name, String state, String country, Coord coord) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.country = country;
        this.coord = coord;
    }

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

    @Override
    public String toString() {
        String answer = "";

        answer += "ID: "+ getId() +"\n";
        answer += "Name: "+ getName() +"\n";
        answer += "Country: "+ getCountry() +"\n";
        answer += "Coord: Lon: "+ getCoord().getLon() +"\n";
        answer += "Coord: Lat: "+ getCoord().getLat()+"\n";
        answer += "-------------------------------- \n";
        return answer;
    }
}
