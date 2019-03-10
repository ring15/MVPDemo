package com.example.founq.iweather.base;

public interface PresenterInterface<V> {

    void register(V view);

    void unRegister();

}
