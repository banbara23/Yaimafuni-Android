package com.ikmr.banbara23.yaeyama_liner_checker.model.weather;

/**
 * 時間毎の天気
 */
public class Table {

    private String hour;
    private String weather;
    private String windBlow;
    private String windSpeed;

    public String getHour() {
        return this.hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getWeather() {
        return this.weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWindBlow() {
        return this.windBlow;
    }

    public void setWindBlow(String windBlow) {
        this.windBlow = windBlow;
    }

    public String getWindSpeed() {
        return this.windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    @Override
    public String toString() {
        return "Table{" +
               "hour='" + hour + '\'' +
               ", weather='" + weather + '\'' +
               ", windBlow='" + windBlow + '\'' +
               ", windSpeed='" + windSpeed + '\'' +
               '}';
    }
}
