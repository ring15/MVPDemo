package com.example.founq.mvpdemo.mvp.presenter;

public interface Presenter <V> {

    void register (V view); //注册activity

    void unRegister();  //取消注册
}
