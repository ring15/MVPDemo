package com.example.founq.iweather.base;


import java.lang.ref.WeakReference;

public class BasePresenter <V> implements PresenterInterface<V> {

    protected WeakReference<V> view;
    protected boolean isAttach;

    @Override
    public void register(V view) {
        this.view = new WeakReference<V>(view);
        isAttach = true;
    }

    @Override
    public void unRegister() {
        view.clear();
        isAttach = false;
    }
}
