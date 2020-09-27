package pl.devtommy.model;

import static java.lang.Math.round;

public class OneDayWeather {
    private String name;
    private int id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        String answer = "";

        if (id !=0) answer += "Id: " + getId() +"\n";
        if (name != null) answer += "Name: " + getName() +"\n";
        answer += "Humidity: " + getHumidity() + "\n";
        answer += "Pressure: " + getPressure() + "\n";
        answer += "Temp: " + getTemp() + "\n";
        if (tempMin != tempMax) {
            answer += "TempMin: " + getTempMin() + "\n";
            answer += "TempMax: " + getTempMax() + "\n";
        }
        answer += "-------------------------------- \n";
        return answer;
    }
}
