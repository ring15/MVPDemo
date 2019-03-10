package com.example.founq.okhttpdemo.base;

import com.example.founq.okhttpdemo.Interfaces.PresenterInter;

import java.lang.ref.WeakReference;

public class BasePresenter <V> implements PresenterInter<V> {

    protected WeakReference<V> view;

    @Override
    public void register(V view) {
        this.view = new WeakReference<V>(view);
    }

    @Override
    public void unRegister() {
        view.clear();
    }
}
