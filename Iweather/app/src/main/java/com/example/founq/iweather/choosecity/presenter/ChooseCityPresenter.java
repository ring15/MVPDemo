package com.example.founq.iweather.choosecity.presenter;

import android.os.AsyncTask;

import com.example.founq.iweather.base.BasePresenter;
import com.example.founq.iweather.choosecity.CityContract;
import com.example.founq.iweather.choosecity.model.ChooseCityModel;
import com.example.founq.iweather.choosecity.view.ChooseCityActivity;
import com.example.founq.iweather.data.Utility;

public class ChooseCityPresenter extends BasePresenter<ChooseCityActivity> implements CityContract.CityPresenterInterface {


    CityContract.CityModelInterface chooseCityModel = new ChooseCityModel();
    private String strOther;

    @Override
    public void getModel(int code) {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                strOther = chooseCityModel.getCity(code);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if (Utility.handleCityResponse(strOther, code)) {
                    view.get().show();
                }
            }

        }.execute();


    }
}
