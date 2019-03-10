package com.example.founq.iweather.gson;

public class FutureWeather {

    private String temperature;
    private String weather;
    private WeatherId weather_id;
    private String wind;
    private String week;
    private String date;


    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public void setWeather_id(WeatherId weather_id) {
        this.weather_id = weather_id;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getTemperature() {
        return temperature;
    }

    public String getWeather() {
        return weather;
    }

    public WeatherId getWeather_id() {
        return weather_id;
    }

    public String getWind() {
        return wind;
    }

    public String getWeek() {
        return week;
    }

    public String getDate() {
        return date;
    }

}
