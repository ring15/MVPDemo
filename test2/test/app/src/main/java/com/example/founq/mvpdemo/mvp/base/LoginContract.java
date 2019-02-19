package com.example.founq.mvpdemo.mvp.base;

public interface LoginContract {

    interface LoginModelInter {

        boolean getresult(String name , String password);

    }

    interface LoginPresenterInter {

        void dologin(String name , String password);

    }

    interface LoginViewInter {

        void show(String string);

    }

}
