package com.example.founq.gaitrecognition.base;


import java.lang.ref.WeakReference;

public class BasePresenter <V> implements PresenterInterface<V> {

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
