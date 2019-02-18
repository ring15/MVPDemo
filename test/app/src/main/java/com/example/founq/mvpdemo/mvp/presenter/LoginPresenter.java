package com.example.founq.mvpdemo.mvp.presenter;


import android.text.TextUtils;

import com.example.founq.mvpdemo.mvp.model.LoginModel;
import com.example.founq.mvpdemo.mvp.model.LoginModelInter;
import com.example.founq.mvpdemo.mvp.view.LoginViewInter;

public class LoginPresenter implements LoginPresenterInter{

    LoginModelInter loginModelInter;
    LoginViewInter loginViewInter;

    public LoginPresenter(LoginViewInter loginViewInter){
        this.loginViewInter = loginViewInter;
        loginModelInter = new LoginModel();
    }

    @Override
    public void dologin(String name, String password) {
        if(TextUtils.isEmpty(name)){
            loginViewInter.show("用户名不能为空！");
        }
        else if(TextUtils.isEmpty(password)){
            loginViewInter.show("密码不能为空！");
        }
        else {
            boolean isRight = loginModelInter.getresult(name,password);
            if(isRight){
                loginViewInter.show("密码正确！");
            }
            else {
                loginViewInter.show("用户名或密码错误！");
            }
        }
    }
}
