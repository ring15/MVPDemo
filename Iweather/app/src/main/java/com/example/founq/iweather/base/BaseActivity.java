package com.example.founq.iweather.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;


public abstract class BaseActivity <P extends BasePresenter> extends Activity implements ViewInterface {

    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindView();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unBindView();
    }

    @Override
    public void bindView() {
        presenter = createPresenter();
        presenter.register(this);
    }

    @Override
    public void unBindView() {
        presenter.unRegister();
    }

    protected abstract P createPresenter();

}
