package pl.devtommy.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static java.lang.Math.round;

public class DayWeather {
    private String name;
    private String country;
    private String description;
    private String mainCondition;
    private int id;
    private LocalDateTime date;
    private double humidity;
    private double pressure;
    private double temp;
    private double tempMin;
    private double tempMax;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setDate(Date date) {
        setDate(convertDateToLocaldatetime(date));
    }

    public LocalDateTime getDate(){
        return date;
    }

    public String getDescription() {
        return firstCapitalLetter(description);
    }

    public void setDescription(String descriptionText) {
        this.description = descriptionText;
    }

    public String getMainCondition() {
        return mainCondition;
    }

    public void setMainCondition(String mainCondition) {
        this.mainCondition = mainCondition;
    }

    public String getHumidity() {
        return String.valueOf(round( humidity )) +"%";
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return String.valueOf(round( pressure )) +"hPa";
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public String getTemp() {
        return String.valueOf(round( temp )) + "°";
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public String getTempMin() {
        return String.valueOf(round( tempMin )) + "°";
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public String getTempMax() {
        return String.valueOf(round( tempMax )) + "°";
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    private String firstCapitalLetter(String text) {
        return text.substring(0,1).toUpperCase() + text.substring(1).toLowerCase();
    }

    private LocalDateTime convertDateToLocaldatetime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    @Override
    public String toString() {
        String answer = "";

        if (id !=0) answer += "Id: " + getId() +"\n";
        if (name != null) answer += "Name: " + getName() +"\n";
        if (country != null) answer += "Country: " + getCountry() +"\n";
        answer += "Humidity: " + getHumidity() + "\n";
        answer += "Pressure: " + getPressure() + "\n";
        answer += "Temp: " + getTemp() + "\n";
        if (tempMin != tempMax) {
            answer += "TempMin: " + getTempMin() + "\n";
            answer += "TempMax: " + getTempMax() + "\n";
        }
        answer += "Main codition: " + getMainCondition() + "\n";
        answer += "Description: " + getDescription() + "\n";
        answer += "-------------------------------- \n";
        return answer;
    }
}
