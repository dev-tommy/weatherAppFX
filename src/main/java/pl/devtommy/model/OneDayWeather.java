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
        System.out.println("Id: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Humidity: " + getHumidity() + " %");
        System.out.println("Pressure: " + getPressure() + " hPa");
        System.out.println("Temp: " + getTemp() + " ℃");
        System.out.println("TempMin: " + getTempMin() + " ℃");
        System.out.println("TempMax: " + getTempMax() + " ℃");

        return "-----------------------";
    }
}
