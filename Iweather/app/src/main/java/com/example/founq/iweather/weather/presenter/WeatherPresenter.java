package com.example.founq.iweather.weather.presenter;

import android.os.AsyncTask;

import com.example.founq.iweather.base.BasePresenter;
import com.example.founq.iweather.base.Listener;
import com.example.founq.iweather.data.Utility;
import com.example.founq.iweather.entity.Error;
import com.example.founq.iweather.gson.FutureWeather;
import com.example.founq.iweather.gson.Weather;
import com.example.founq.iweather.weather.WeatherContract;
import com.example.founq.iweather.weather.model.WeatherModel;
import com.example.founq.iweather.weather.view.MainActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class WeatherPresenter extends BasePresenter<MainActivity> implements WeatherContract.WeatherPresenterInterface {

    WeatherContract.WeatherModelInterface modelInterface = new WeatherModel();
    private String strOther;
    private Weather weather;


    String[] wid = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17",
            "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "53"};

    String[] weatherName = {"晴", "多云", "阴", "阵雨", "雷阵雨", "雷阵雨伴有冰雹", "雨夹雪", "小雨", "中雨", "大雨", "暴雨", "大暴雨", "特大暴雨", "阵雪", "小雪", "中雪", "大雪", "暴雪", "雾",
            "冻雨", "沙尘暴", "小雨-中雨", "中雨-大雨", "大雨-暴雨", "暴雨-大暴雨", "大暴雨-特大暴雨", "小雪-中雪", "中雪-大雪", "大雪-暴雪", "浮尘", "扬沙", "强沙尘暴", "霾"};


    HashMap<String, String> map = new HashMap<>();

    public WeatherPresenter() {
        for (int i = 0; i < wid.length; i++) {
            map.put(wid[i], weatherName[i]);
        }
    }


    @Override
    public void getModel(String city) {

        modelInterface.getWeather(city, new Listener<String>() {
            @Override
            public void onSuccess(String data, Object... params) {
                strOther = data;
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        weather = Utility.handleWeatherResponse(strOther);
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        if (weather != null) {
                            view.get().show(weather);
                        } else {
                            view.get().showMassage();
                        }
                    }

                }.execute();
            }

            @Override
            public void onFailure(Error error) {

            }
        });


    }

    @Override
    public void showWeather(String fa, String fb) {
        for (int i = 0; i < wid.length; i++) {
            if (fa.equals(wid[i])) {
                view.get().showImage(i);
            }
        }
    }

    @Override
    public void judgTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        String hour = sdf.format(new Date());
        int k = Integer.parseInt(hour);
        if ((k >= 0 && k < 6) || (k >= 18 && k < 24)) {
            view.get().shownight();
        } else {
            view.get().showDay();
        }
    }

    @Override
    public void judeWeather() {
        List<FutureWeather> futureWeathers = weather.getFutureWeathers();
        List<Integer> contant = new ArrayList<>();
        for (int i = 0; i < futureWeathers.size(); i++) {
            for (int j = 0; j < wid.length; j++) {
                String fa = futureWeathers.get(i).getWeather_id().getFa();
                if (fa.equals(wid[j])) {
                    contant.add(j);
                }
            }
        }
        view.get().showImages(contant);
    }
}
