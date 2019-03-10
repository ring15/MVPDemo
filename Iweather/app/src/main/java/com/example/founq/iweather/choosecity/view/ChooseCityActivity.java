package com.example.founq.iweather.choosecity.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.founq.iweather.R;
import com.example.founq.iweather.adapter.ProvinceAdapter;
import com.example.founq.iweather.base.BaseActivity;
import com.example.founq.iweather.choosecity.CityContract;
import com.example.founq.iweather.choosecity.presenter.ChooseCityPresenter;
import com.example.founq.iweather.chooseprovince.view.ChooseProvinceActivity;
import com.example.founq.iweather.entity.City;
import com.example.founq.iweather.entity.Province;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class ChooseCityActivity extends BaseActivity<ChooseCityPresenter> implements CityContract.CityViewInterface {


    Button btnBack;
    TextView tvTitle;
    RecyclerView recyclerCity;
    ProvinceAdapter provinceAdapter;
    String name;
    int code;

    private List<String> dataList = new ArrayList<>();
    private List<Integer> codeList = new ArrayList<>();
    private List<City> cityList = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_province);
        findView();
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        code = intent.getIntExtra("code", 0);
        presenter.getModel(code);
    }

    private void findView() {
        btnBack = findViewById(R.id.btn_back);
        tvTitle = findViewById(R.id.tv_title);
        recyclerCity = findViewById(R.id.recycler_province);
        tvTitle.setText(name);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    protected ChooseCityPresenter createPresenter() {
        return new ChooseCityPresenter();
    }

    @Override
    public void show() {
        cityList.clear();
        cityList = LitePal.findAll(City.class);
        if (cityList.size() > 0) {
            dataList.clear();
            codeList.clear();
            for (City city : cityList) {
                dataList.add(city.getCityName());
                codeList.add(city.getCityCode());
            }

            provinceAdapter = new ProvinceAdapter(ChooseCityActivity.this, dataList, codeList, 1,code);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerCity.setLayoutManager(layoutManager);
            recyclerCity.setAdapter(provinceAdapter);
        }
    }

}
