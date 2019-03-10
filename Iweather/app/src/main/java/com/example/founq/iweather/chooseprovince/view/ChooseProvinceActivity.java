package com.example.founq.iweather.chooseprovince.view;

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
import com.example.founq.iweather.chooseprovince.presenter.ChooseProvincePresenter;
import com.example.founq.iweather.chooseprovince.ProvinceContract;
import com.example.founq.iweather.entity.Province;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class ChooseProvinceActivity extends BaseActivity<ChooseProvincePresenter> implements ProvinceContract.ProvinceViewInterface{

    Button btnBack;
    TextView tvTitle;
    RecyclerView recyclerProvince;
    ProvinceAdapter provinceAdapter;

    private List<String> dataList = new ArrayList<>();
    private List<Integer> codeList = new ArrayList<>();
    private List<Province> provinceList = new ArrayList<>();
    private boolean isSplsh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_province);
        findView();
        presenter.getModel();
        Intent intent = getIntent();
        isSplsh = intent.getBooleanExtra("from",false);
    }

    private void findView() {
        btnBack = findViewById(R.id.btn_back);
        tvTitle = findViewById(R.id.tv_title);
        if(isSplsh){
            btnBack.setVisibility(View.GONE);
        }
        else {
            btnBack.setVisibility(View.VISIBLE);
        }
        recyclerProvince = findViewById(R.id.recycler_province);
        tvTitle.setText(R.string.country);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected ChooseProvincePresenter createPresenter() {
        return new ChooseProvincePresenter();
    }


    @Override
    public void show() {

        provinceList.clear();
        provinceList = LitePal.findAll(Province.class);
        if (provinceList.size() > 0) {
            dataList.clear();
            codeList.clear();
            for (Province province : provinceList) {
                dataList.add(province.getProvinceName());
                codeList.add(province.getProvinceCode());
            }

            provinceAdapter = new ProvinceAdapter(ChooseProvinceActivity.this,dataList, codeList, 0,0);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerProvince.setLayoutManager(layoutManager);
            recyclerProvince.setAdapter(provinceAdapter);
        }
    }
}
