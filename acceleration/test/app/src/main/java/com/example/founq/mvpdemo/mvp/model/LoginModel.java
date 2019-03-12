package com.example.founq.mvpdemo.mvp.model;

import com.example.founq.mvpdemo.mvp.base.LoginContract;

public class LoginModel implements LoginContract.LoginModelInter {


    @Override
    public boolean getresult(String name , String password) {

        //从服务器端获取数据
        //虚拟数据
        if(name.equals("123")&password.equals("123")){
            return true;
        }
        else
            return false;

    }
}
