package com.example.founq.okhttpdemo.Interfaces;


public interface InterCon {

    interface modelInter{
        String getPostData(String string);
        String getGetData();
    }

    interface viewInter{
        void show(String string);
    }

    interface presenterInter{
        void doPost(String string);
        void doGet();
    }

}
