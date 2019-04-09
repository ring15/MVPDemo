package com.example.founq.gaitrecognition.mvp.prsenter;

import android.hardware.SensorManager;

import com.example.founq.gaitrecognition.MainActivity;
import com.example.founq.gaitrecognition.base.BasePresenter;
import com.example.founq.gaitrecognition.mvp.Contract;

public class MainPresenter extends BasePresenter<MainActivity> implements Contract.Presenter {
    @Override
    public void pass() {
        view.get().show();
    }

    @Override
    public void getGaitInfo(boolean isRecord) {
        if (isRecord){
            view.get().stopRecord();
        }else {
            view.get().showGaitInfo();
        }
    }
}
