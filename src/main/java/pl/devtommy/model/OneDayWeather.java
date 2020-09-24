package pl.devtommy.model;

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

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    @Override
    public String toString() {
        String answer = "";

        answer += "Id: " + getId() +"\n";
        answer += "Name: " + getName() +"\n";
        answer += "Humidity: " + getHumidity() + " % \n";
        answer += "Pressure: " + getPressure() + " hPa \n";
        answer += "Temp: " + getTemp() + " ℃ \n";
        answer += "TempMin: " + getTempMin() + " ℃ \n";
        answer += "TempMax: " + getTempMax() + " ℃ \n";
        answer += "-------------------------------- \n";
        return answer;
    }
}
