package com.example.founq.okhttpdemo.mvp;

import android.os.AsyncTask;
import android.util.Log;

import com.example.founq.okhttpdemo.Interfaces.InterCon;
import com.example.founq.okhttpdemo.MainActivity;
import com.example.founq.okhttpdemo.base.BasePresenter;

public class Presenter extends BasePresenter<MainActivity> implements InterCon.presenterInter {

    InterCon.modelInter modelInter = new Model();

    static String strOther;
    static String strGet;

    @Override
    public void doPost(String string) {
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                strOther = modelInter.getPostData(string);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                view.get().show(strOther);
            }

        }.execute();
    }

    @Override
    public void doGet() {
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                strGet = modelInter.getGetData();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                view.get().show(strGet);
            }

        }.execute();
    }
}
