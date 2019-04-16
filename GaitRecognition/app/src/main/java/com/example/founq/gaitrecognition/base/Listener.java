package com.example.founq.gaitrecognition.base;

public interface Listener<T> {
    public abstract void Success(T body, String... strings);

    public abstract void Failed(String fail);
}
