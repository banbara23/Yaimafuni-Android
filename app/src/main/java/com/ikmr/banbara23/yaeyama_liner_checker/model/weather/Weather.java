package com.ikmr.banbara23.yaeyama_liner_checker.model.weather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 天気 一日分
 */
public class Weather {

    @SerializedName("date")
    private String date;
    @SerializedName("table")
    private List<Table> table;
    @SerializedName("temperature")
    private Temperature temperature;
    @SerializedName("wave")
    private String wave;
    @SerializedName("weather")
    private String weather;
    @SerializedName("wind")
    private String wind;

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }

    public List<Table> getTable() {
        return table;
    }

    public void setTable(List<Table> table) {
        this.table = table;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public Temperature getTemperature() {
        return this.temperature;
    }

    public void setWave(String wave) {
        this.wave = wave;
    }

    public String getWave() {
        return this.wave;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWeather() {
        return this.weather;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getWind() {
        return this.wind;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "date='" + date + '\'' +
                ", table=" + table +
                ", temperature=" + temperature +
                ", wave='" + wave + '\'' +
                ", weather='" + weather + '\'' +
                ", wind='" + wind + '\'' +
                '}';
    }
}