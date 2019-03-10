package com.example.founq.iweather.weather.model;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.founq.iweather.base.Listener;
import com.example.founq.iweather.gson.Weather;
import com.example.founq.iweather.weather.WeatherContract;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherModel implements WeatherContract.WeatherModelInterface {

    String responseText = "null";

    @Override
    public void getWeather(String city, Listener listener) {

        String url = "http://v.juhe.cn/weather/index?cityname=" + city + "&key=d06a5b3f515f6946105b644481246662";
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                responseText = response.body().string();
                Log.e("result",responseText);
                listener.onSuccess(responseText);
            }
        });

    }

}
