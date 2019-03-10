package com.example.founq.iweather.weather.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.founq.iweather.R;
import com.example.founq.iweather.adapter.MainAdapter;
import com.example.founq.iweather.base.BaseActivity;
import com.example.founq.iweather.chooseprovince.view.ChooseProvinceActivity;
import com.example.founq.iweather.gson.Weather;
import com.example.founq.iweather.weather.WeatherContract;
import com.example.founq.iweather.weather.presenter.WeatherPresenter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity<WeatherPresenter> implements WeatherContract.WeatherViewInterface {

    TextView tvTitle, tvToday, tvWeather, tvTemp, tvCurrentTemp, tvWndDirection, tvWindPower, tvHumidity, tvUv, tvDressIndex, tvDressAdvice, tvExercise, tvWash, tvTravel;
    RecyclerView rcySeven;
    ImageView ivWeather;
    LinearLayout layoutMain, layoutChange;
    SearchView searchView;
    Weather weather = new Weather();
    MainAdapter mainAdapter;
    List<Integer> contant = new ArrayList<>();

    int[] imageIcon = {R.drawable.icon_1,R.drawable.icon_2,R.drawable.icon_3,R.drawable.icon_4,R.drawable.icon_5,R.drawable.icon_6,R.drawable.icon_7,R.drawable.icon_8,R.drawable.icon_9,R.drawable.icon_10,
            R.drawable.icon_11,R.drawable.icon_12,R.drawable.icon_13,R.drawable.icon_14,R.drawable.icon_15,R.drawable.icon_16,R.drawable.icon_17,R.drawable.icon_18,R.drawable.icon_19,R.drawable.icon_20,
            R.drawable.icon_21,R.drawable.icon_22,R.drawable.icon_23,R.drawable.icon_24,R.drawable.icon_25,R.drawable.icon_26,R.drawable.icon_27,R.drawable.icon_28,R.drawable.icon_29,R.drawable.icon_30,
            R.drawable.icon_31,R.drawable.icon_32,R.drawable.icon_33};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findview();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String string = sharedPreferences.getString("city", null);
        presenter.getModel(string);
        tvTitle.setText(string);

    }

    private void findview() {
        tvTitle = findViewById(R.id.tv_title);
        layoutChange = findViewById(R.id.layout_change);
        layoutMain = findViewById(R.id.layou_main);
        searchView = findViewById(R.id.search);
        tvToday = findViewById(R.id.tv_today);
        tvWeather = findViewById(R.id.tv_weather);
        tvTemp = findViewById(R.id.tv_temp);
        tvCurrentTemp = findViewById(R.id.tv_current_temp);
        tvWndDirection = findViewById(R.id.tv_wind_direction);
        tvWindPower = findViewById(R.id.tv_wind_power);
        tvHumidity = findViewById(R.id.tv_humidity);
        tvUv = findViewById(R.id.tv_uv);
        tvDressIndex = findViewById(R.id.tv_dress_index);
        tvDressAdvice = findViewById(R.id.tv_dress_advice);
        tvExercise = findViewById(R.id.tv_exercise);
        tvWash = findViewById(R.id.tv_wash);
        tvTravel = findViewById(R.id.tv_travel);
        rcySeven = findViewById(R.id.rcy_seven);
        ivWeather = findViewById(R.id.iv_weather);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
             @Override
             public boolean onQueryTextSubmit(String query) {
                 presenter.getModel(query);
                 return false;
             }
             // 当搜索内容改变时触发该方法
             @Override
             public boolean onQueryTextChange(String newText) {
                 return false;
             }
        });

        presenter.judgTime();
        layoutChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChooseProvinceActivity.class);
                intent.putExtra("from", false);
                startActivity(intent);
            }
        });
    }

    @Override
    protected WeatherPresenter createPresenter() {
        return new WeatherPresenter();
    }

    @Override
    public void show(Weather weather) {
        this.weather = weather;
        tvTitle.setText(weather.getTodayWeather().getCity());
        tvToday.setText(weather.getTodayWeather().getDate_y()+"   "+weather.getTodayWeather().getWeek());
        tvWeather.setText(weather.getTodayWeather().getWeather());
        tvTemp.setText(weather.getCurrentWeather().getTemp()+"°");
        tvCurrentTemp.setText(weather.getTodayWeather().getTemperature());
        tvWndDirection.setText(weather.getCurrentWeather().getWind_direction());
        tvWindPower.setText(weather.getCurrentWeather().getWind_strength());
        tvHumidity.setText(weather.getCurrentWeather().getHumidity());
        tvUv.setText(weather.getTodayWeather().getUv_index());
        tvDressIndex.setText(weather.getTodayWeather().getDressing_index());
        tvDressAdvice.setText(weather.getTodayWeather().getDressing_advice());
        tvExercise.setText(weather.getTodayWeather().getExercise_index());
        tvWash.setText(weather.getTodayWeather().getWash_index());
        tvTravel.setText(weather.getTodayWeather().getTravel_index());
        presenter.judeWeather();
        mainAdapter = new MainAdapter(this,weather.getFutureWeathers(),contant);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rcySeven.setLayoutManager(layoutManager);
        rcySeven.setAdapter(mainAdapter);
        presenter.showWeather(weather.getTodayWeather().getWeather_id().getFa(), weather.getTodayWeather().getWeather_id().getFb());
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("city",weather.getTodayWeather().getCity());
        editor.commit();
    }

    @Override
    public void showDay() {
        layoutMain.setBackgroundResource(R.drawable.background_day);
    }

    @Override
    public void shownight() {
        layoutMain.setBackgroundResource(R.drawable.background_night);
    }

    @Override
    public void showImage(int i) {
        ivWeather.setImageResource(imageIcon[i]);
    }

    @Override
    public void showImages(List<Integer> contant) {
        this.contant = contant;
    }

    @Override
    public void showMassage() {
        Toast.makeText(this,"获取信息失败",Toast.LENGTH_SHORT).show();
    }


}
