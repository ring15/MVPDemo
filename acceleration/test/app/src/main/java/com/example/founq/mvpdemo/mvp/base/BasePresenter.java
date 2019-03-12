package com.example.founq.mvpdemo.mvp.base;


import com.example.founq.mvpdemo.mvp.presenter.Presenter;

import java.lang.ref.WeakReference;

public class BasePresenter <V> implements Presenter<V> {

    protected WeakReference<V> view;  //弱引用，防止内存泄漏

    @Override
    public void register(V view) {
        this.view = new WeakReference<V>(view);
    }

    @Override
    public void unRegister() {
        view.clear();
    }
}
