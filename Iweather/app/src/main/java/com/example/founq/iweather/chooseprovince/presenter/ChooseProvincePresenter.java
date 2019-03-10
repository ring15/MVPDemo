package com.example.founq.iweather.chooseprovince.presenter;

import android.os.AsyncTask;

import com.example.founq.iweather.base.BasePresenter;
import com.example.founq.iweather.chooseprovince.ProvinceContract;
import com.example.founq.iweather.chooseprovince.model.ChooseProvinceModel;
import com.example.founq.iweather.chooseprovince.view.ChooseProvinceActivity;
import com.example.founq.iweather.data.Utility;

public class ChooseProvincePresenter extends BasePresenter<ChooseProvinceActivity> implements ProvinceContract.ProvincePresenterInterface {

    ProvinceContract.ProvinceModelInterface chooseProvinceModel = new ChooseProvinceModel();
    private String strOther;

    @Override
    public void getModel() {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                strOther = chooseProvinceModel.getProvince();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if(Utility.handleProvinceResponse(strOther)){
                    view.get().show();
                }
            }

        }.execute();

    }
}
