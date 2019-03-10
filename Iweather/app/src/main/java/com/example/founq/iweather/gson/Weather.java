package com.example.founq.iweather.gson;


import java.util.List;

public class Weather {

    private CurrentWeather currentWeather;
    private TodayWeather todayWeather;
    private List<FutureWeather> futureWeathers;

    public void setCurrentWeather(CurrentWeather currentWeather) {
        this.currentWeather = currentWeather;
    }

    public void setTodayWeather(TodayWeather todayWeather) {
        this.todayWeather = todayWeather;
    }

    public void setFutureWeathers(List<FutureWeather> futureWeathers) {
        this.futureWeathers = futureWeathers;
    }


    public CurrentWeather getCurrentWeather() {
        return currentWeather;
    }

    public TodayWeather getTodayWeather() {
        return todayWeather;
    }

    public List<FutureWeather> getFutureWeathers() {
        return futureWeathers;
    }
}
