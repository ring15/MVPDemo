package com.example.founq.gaitrecognition.base;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class BaseRequst {

    private static final String  BASE_URL = "http://192.168.43.74:5000/";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL);

    private static Retrofit retrofit = builder.client(httpClient.build()).build();

    public static <T>T getInstance(Class<T> tClass){
        return retrofit.create(tClass);
    }

}
