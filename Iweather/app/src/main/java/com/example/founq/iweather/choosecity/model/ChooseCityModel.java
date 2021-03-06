package com.example.founq.iweather.choosecity.model;

import android.util.Log;

import com.example.founq.iweather.choosecity.CityContract;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ChooseCityModel implements CityContract.CityModelInterface {


    String responseText = "null";
    @Override
    public String getCity(int code) {

        String url = "http://guolin.tech/api/china/"+code;
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            responseText= response.body().string();
            Log.e("responseText1",responseText);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e("responseText",responseText);
        return responseText;
    }

}
