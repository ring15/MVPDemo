package com.example.founq.gaitrecognition.base;

public interface PresenterInterface<V> {

    void register(V view);

    void unRegister();

}
