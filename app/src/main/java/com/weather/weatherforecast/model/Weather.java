package com.weather.weatherforecast.model;

import java.io.Serializable;

public class Weather implements Serializable {
    private String location;
    private int temp;
    private int tempMin;
    private int tempMax;
    private long date;
    private int humidity;
    private String status;
    private String main;
    private String icon;
    private Double tempFeel;

    public Weather(String location, int temp, int tempMin, int tempMax, long date, int humidity, String status, String main, String icon, Double tempFeel) {
        this.location = location;
        this.temp = temp;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.date = date;
        this.humidity = humidity;
        this.status = status;
        this.main = main;
        this.icon = icon;
        this.tempFeel = tempFeel;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getTempMin() {
        return tempMin;
    }

    public void setTempMin(int tempMin) {
        this.tempMin = tempMin;
    }

    public int getTempMax() {
        return tempMax;
    }

    public void setTempMax(int tempMax) {
        this.tempMax = tempMax;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Double getTempFeel() {
        return tempFeel;
    }

    public void setTempFeel(Double tempFeel) {
        this.tempFeel = tempFeel;
    }
}
