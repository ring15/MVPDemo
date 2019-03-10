package com.example.founq.okhttpdemo.base;

import android.app.Application;

public class MyApplication extends Application {

    private static MyApplication instance = null;

    public  static MyApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
