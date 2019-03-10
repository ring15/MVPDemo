package com.example.founq.iweather.base;

import com.example.founq.iweather.entity.Error;
public interface Listener<T> {
    void onSuccess(T data, Object... params);
    void onFailure(Error error);
}