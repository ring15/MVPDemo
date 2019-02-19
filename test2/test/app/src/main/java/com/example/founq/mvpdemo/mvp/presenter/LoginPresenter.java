package com.example.founq.mvpdemo.mvp.presenter;


import android.text.TextUtils;

import com.example.founq.mvpdemo.mvp.base.BasePresenter;
import com.example.founq.mvpdemo.mvp.base.LoginContract;
import com.example.founq.mvpdemo.mvp.model.LoginModel;
import com.example.founq.mvpdemo.ui.LoginActivity;

/**
 * @author ring
 */
public class LoginPresenter extends BasePresenter<LoginActivity> implements LoginContract.LoginPresenterInter {

    LoginContract.LoginModelInter loginModelInter = new LoginModel();
//    LoginViewInter loginViewInter;

//    public LoginPresenter(LoginViewInter loginViewInter){
//        this.loginViewInter = loginViewInter;
//        loginModelInter = new LoginModel();
//    }

    @Override
    public void dologin(String name, String password) {
        if(TextUtils.isEmpty(name)){
            view.get().show("用户名不能为空！");//view.get()获取loginViewInter，为什么要get？
        }
        else if(TextUtils.isEmpty(password)){
            view.get().show("密码不能为空！");
        }
        else {
            boolean isRight = loginModelInter.getresult(name,password);
            if(isRight){
                view.get().show("密码正确！");
            }
            else {
                view.get().show("用户名或密码错误！");
            }
        }
    }
}
