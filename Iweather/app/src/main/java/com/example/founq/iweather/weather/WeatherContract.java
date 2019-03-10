package com.example.founq.iweather.weather;

import com.example.founq.iweather.base.Listener;
import com.example.founq.iweather.gson.Weather;

import java.util.List;

public interface WeatherContract {


    interface WeatherModelInterface {
        void getWeather(String city, Listener listener);
    }

    interface WeatherPresenterInterface {
        void getModel(String city);
        void showWeather(String fa, String fb);
        void judgTime();
        void judeWeather();
    }

    interface WeatherViewInterface {
        void show(Weather weather);
        void showDay();
        void shownight();
        void showImage(int i);
        void showImages(List<Integer> contant);
        void showMassage();
    }

}
