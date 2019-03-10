package com.example.founq.iweather.chooseprovince.model;

import android.util.Log;

import com.example.founq.iweather.chooseprovince.ProvinceContract;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ChooseProvinceModel implements ProvinceContract.ProvinceModelInterface {

    String responseText = "null";
    @Override
    public String getProvince() {

        String url = "http://guolin.tech/api/china";
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
