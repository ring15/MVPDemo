package com.example.founq.iweather.choosecountry.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.founq.iweather.R;
import com.example.founq.iweather.adapter.ProvinceAdapter;
import com.example.founq.iweather.base.BaseActivity;
import com.example.founq.iweather.choosecountry.CountryContract;
import com.example.founq.iweather.choosecountry.presenter.ChooseCountryPresenter;
import com.example.founq.iweather.entity.City;
import com.example.founq.iweather.entity.Country;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class ChooseCountryActivity extends BaseActivity<ChooseCountryPresenter> implements CountryContract.CountryViewInterface{


    Button btnBack;
    TextView tvTitle;
    RecyclerView recyclerCity;
    ProvinceAdapter provinceAdapter;
    String name;

    private List<String> dataList = new ArrayList<>();
    private List<Integer> codeList = new ArrayList<>();
    private List<Country> countryList = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_province);
        findView();
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        int code = intent.getIntExtra("code", 0);
        int citycode = intent.getIntExtra("citycode", 0);
        presenter.getModel(code,citycode);
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
    protected ChooseCountryPresenter createPresenter() {
        return new ChooseCountryPresenter();
    }

    @Override
    public void show() {
        countryList.clear();
        countryList = LitePal.findAll(Country.class);
        if (countryList.size() > 0) {
            dataList.clear();
            codeList.clear();
            for (Country country : countryList) {
                dataList.add(country.getCountyName());
                String s = country.getWeatherId();

                codeList.add(Integer.parseInt(s.substring(3)));
            }

            provinceAdapter = new ProvinceAdapter(ChooseCountryActivity.this, dataList, codeList, 2,0);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerCity.setLayoutManager(layoutManager);
            recyclerCity.setAdapter(provinceAdapter);
        }
    }

}
