package com.example.founq.iweather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.founq.iweather.chooseprovince.view.ChooseProvinceActivity;
import com.example.founq.iweather.weather.view.MainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        new Handler().postDelayed(new Runnable() {
            public void run() {

                if(sharedPreferences.getString("city",null) != null){
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                } else {
                    Intent intent = new Intent(SplashActivity.this,ChooseProvinceActivity.class);
                    intent.putExtra("from",true);
                    startActivity(intent);
                }
                finish();
            }
        }, 3000);
    }
}
