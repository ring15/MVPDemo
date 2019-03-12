package com.example.founq.mvpdemo.mvp.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.founq.mvpdemo.mvp.view.LoginView;

public abstract class BaseActivity <P extends BasePresenter> extends Activity implements LoginView {


    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindView();//create时绑定
    }

    @Override
    protected void onStop() {
        super.onStop();
        unBindView();//stop时解绑
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
