package com.example.founq.okhttpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.founq.okhttpdemo.Interfaces.InterCon;
import com.example.founq.okhttpdemo.base.BaseActivity;
import com.example.founq.okhttpdemo.mvp.Presenter;

public class MainActivity extends BaseActivity<Presenter> implements InterCon.viewInter ,View.OnClickListener {

    Button btnPost,btnGet;
    TextView tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPost = findViewById(R.id.btn_post);
        btnGet = findViewById(R.id.btn_get);
        tvShow = findViewById(R.id.tv_show);
        btnPost.setOnClickListener(this);
        btnGet.setOnClickListener(this);
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter();
    }

    @Override
    public void show(String string) {
        tvShow.setText(string);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_post:
                presenter.doPost("123");
                break;
            case R.id.btn_get:
                presenter.doGet();
                break;
        }
    }
}
