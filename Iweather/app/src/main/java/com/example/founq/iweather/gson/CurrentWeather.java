package com.example.founq.iweather.gson;

public class CurrentWeather {/*当前实况天气*/

    public int getTemp() {
        return temp;
    }

    public String getWind_direction() {
        return wind_direction;
    }

    public String getWind_strength() {
        return wind_strength;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getTime() {
        return time;
    }
    public void setTemp(int temp) {
        this.temp = temp;
    }

    public void setWind_direction(String wind_direction) {
        this.wind_direction = wind_direction;
    }

    public void setWind_strength(String wind_strength) {
        this.wind_strength = wind_strength;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String wind_direction;/*当前风向*/
    private String wind_strength;/*当前风力*/
    private String humidity;/*当前湿度*/
    private String time;/*更新时间*/
    private int temp;/*当前温度*/



}
