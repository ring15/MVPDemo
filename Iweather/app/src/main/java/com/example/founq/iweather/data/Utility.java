package com.example.founq.iweather.data;

import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;


import com.alibaba.fastjson.JSON;
import com.example.founq.iweather.entity.City;
import com.example.founq.iweather.entity.Country;
import com.example.founq.iweather.entity.Error;
import com.example.founq.iweather.entity.Province;
import com.example.founq.iweather.gson.CurrentWeather;
import com.example.founq.iweather.gson.FutureWeather;
import com.example.founq.iweather.gson.TodayWeather;
import com.example.founq.iweather.gson.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Utility {

    /**
     * 解析和处理服务器返回的省级数据
     */
    public static boolean handleProvinceResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allProvinces = new JSONArray(response);
                SQLiteDatabase db = LitePal.getDatabase();
                LitePal.deleteAll(Province.class);
                for (int i = 0; i < allProvinces.length(); i++) {
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的市级数据
     */
    public static boolean handleCityResponse(String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCities = new JSONArray(response);
                LitePal.deleteAll(City.class);
                for (int i = 0; i < allCities.length(); i++) {
                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的县级数据
     */
    public static boolean handleCountyResponse(String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCounties = new JSONArray(response);
                LitePal.deleteAll(Country.class);
                for (int i = 0; i < allCounties.length(); i++) {
                    JSONObject countyObject = allCounties.getJSONObject(i);
                    Country county = new Country();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 将返回的JSON数据解析成Weather实体类
     */
    public static Weather handleWeatherResponse(String response) {
        try {
            com.alibaba.fastjson.JSONObject root = JSON.parseObject(response);    //解析根节点
            String status = root.getString("reason");   //解析状态
            com.alibaba.fastjson.JSONObject data = root.getJSONObject("result");   //解析data节点
            if (data != null){
                com.alibaba.fastjson.JSONObject sk = data.getJSONObject("sk");
                CurrentWeather currentWeather = JSON.toJavaObject(sk,CurrentWeather.class);
                com.alibaba.fastjson.JSONObject today = data.getJSONObject("today");
                TodayWeather todayWeather = JSON.toJavaObject(today,TodayWeather.class);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
                Calendar calendar = Calendar.getInstance();
                com.alibaba.fastjson.JSONObject future = data.getJSONObject("future");
                List<FutureWeather> futureWeathers = new ArrayList<>();
                for(int i =0; i<7; i++){
                    com.alibaba.fastjson.JSONObject first = future.getJSONObject("day_"+simpleDateFormat.format(calendar.getTime()));
                    futureWeathers.add(JSON.toJavaObject(first,FutureWeather.class));
                    calendar.add(Calendar.DAY_OF_MONTH,1);
                }
                Weather weather = new Weather();
                weather.setCurrentWeather(currentWeather);
                weather.setTodayWeather(todayWeather);
                weather.setFutureWeathers(futureWeathers);
                return weather;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

}
